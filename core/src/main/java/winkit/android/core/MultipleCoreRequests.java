package winkit.android.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Class for to make multiple requests from {@link CoreController}
 *
 * Created by zoid23 on 14/06/17.
 */

public class MultipleCoreRequests {

    private List<CtrlRequest> requests;
    private boolean isInError = false;
    private int finishCount = 0;

    /**
     * Constructor
     *
     * @param requests the list of {@link CtrlRequest} to execute on {@link #start(MultipleCoreRequestsResponse)}
     */
    public MultipleCoreRequests (CtrlRequest ... requests){
        this.requests = Arrays.asList(requests);
    }

    /**
     * Launch all requests in the same time.
     *
     * @param listener the listener for all calls successes or for the first error.
     */
    public void start (final MultipleCoreRequestsResponse listener){
        try {
            for(int i = 0; i<requests.size(); i++){
                final CtrlRequest r = requests.get(i);
                r.request(new CtrlRequestListener() {
                    @Override
                    public void onFinish(boolean success) {
                        finishCount ++;
                        if(!success) isInError = true;

                        if(finishCount == requests.size())
                            listener.onAllFinished(!isInError);
                    }
                });
            }
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Class rappresenting the {@link CoreController} request
     *
     * @param <T> the class of result callback
     */
    public static class CtrlRequest <T> {
        private Method method;
        private CoreController controller;
        private ArrayList<Object> params = new ArrayList<>();
        private T result;
        private String errorMessage;

        /**
         * Constructor
         *
         * @param controller the controller instance
         * @param methodName The controller method name
         * @param params the request params used from the {@link CoreController} for to execute the method
         *                    (excluding the {@link winkit.android.core.CoreController.ParsedResponse} param, used as last param).
         * @throws IllegalStateException
         */
        public CtrlRequest (CoreController controller, String methodName, Object ... params) throws IllegalStateException {
            this.params.addAll(Arrays.asList(params));
            Class [] allParamsTypes = new Class[params.length +1];
            for(int i = 0; i<params.length; i++)
                allParamsTypes[i] = params[i].getClass();
            allParamsTypes[params.length] = CoreController.ParsedResponse.class;

            try {
                this.method = controller.getClass().getMethod(methodName, allParamsTypes);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(e);
            }
            this.controller = controller;
        }

        private void request (final CtrlRequestListener listener) throws InvocationTargetException, IllegalAccessException {
            params.add(new CoreController.ParsedResponse<T>(){
                @Override
                public void onSuccess(T t) {
                    result = t;
                    listener.onFinish(true);
                }

                @Override
                public void onError(String error) {
                    errorMessage = error;
                    listener.onFinish(false);
                }
            });
            method.invoke(controller, params.toArray());
        }

        /**
         * @return the request result T object
         */
        public T getResult (){
            return result;
        }


        /**
         * @return the error message
         */
        public String getError (){
            return errorMessage;
        }
    }

    private interface CtrlRequestListener {
        void onFinish (boolean success);
    }

    public interface MultipleCoreRequestsResponse {
        void onAllFinished (boolean allSuccess);
    }
}
