package by.wink.core;

import by.wink.core.rest.CoreRest;
import by.wink.core.uielements.VolleyUi;

/**
 * The base controller for all equests, inizializated on view, is a bridge for to convert the {@link CoreRest} results
 *  into {@link android.os.Parcelable } models used from the views.
 *
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreController<R extends CoreRest> {

    /** the {@link by.wink.core.uielements.VolleyUi} instance */
    private VolleyUi view;

    /** the {@link by.wink.core.rest.CoreRest} instance used for the requests */
    protected R rest;

    /**
     * The default constructor
     *
     * @param view The controller {@link VolleyUi}
     * @param restClass the {@link Class} extends {@link CoreRest}
     */
    public CoreController (VolleyUi view, Class<R> restClass){
        this.view = view;
        try {
            this.rest = restClass.getConstructor(VolleyUi.class).newInstance(view);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("invalid CoreRest.class passed to a CoreController, please use by.wink.core.rest.CoreRest class");
        }
    }

    /**
     * Return the {@link #view} instance.
     * @return the joined {@link VolleyUi}
     */
    public VolleyUi getView() {
        return view;
    }

    /**
     * The interface used for to send the translated model to the view
     *
     * @param <T> The model class
     */
    public interface ParsedResponse<T> {
        /**
         * On request success
         *
         * @param t the result object
         */
        void onSuccess(T t);

        /**
         * On request error
         *
         * @param error the error message from {@link com.android.volley.VolleyError}
         */
        void onError(String error);
    }
}
