package by.wink.core.uielements;

import android.app.IntentService;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * A {@link IntentService} class with the RequestQueue of the currents {@link by.wink.core.rest.CoreRest} requests
 *
 * Created by zoid23 on 06/11/17.
 */

public abstract class CoreIntentService extends IntentService implements VolleyUi{


    private static final String TAG = "SERVICE_VOLLEY";

    /** The {@link RequestQueue} */
    RequestQueue requestsQueue;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CoreIntentService(String name) {
        super(name);
    }

    /**
     * Add the {@link Request} at the {@link #requestsQueue}
     *
     * @param request the request to add
     * @return the request passed as a param
     */
    public Request bindToRequest (Request request){
        if(requestsQueue == null)
            requestsQueue = Volley.newRequestQueue(getContext());

        request.setTag(TAG);
        requestsQueue.add(request);
        return request;
    }


    @Override
    public void onDestroy() {
        cancellAllRequests();
        super.onDestroy();
    }

    /**
     * Return the current {@link Context}
     * @return context
     */
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * Cancel all volley request started from {@link by.wink.core.CoreController} connected whit this {@link VolleyUi} instance.
     * Called on {@link #onDestroy()}
     */
    public void cancellAllRequests (){
        if(requestsQueue != null)
            requestsQueue.cancelAll(TAG);
    }
}
