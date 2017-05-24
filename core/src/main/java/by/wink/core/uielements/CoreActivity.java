package by.wink.core.uielements;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * A {@link AppCompatActivity} class with the RequestQueue of the currents {@link by.wink.core.rest.CoreRest} requests
 *
 * Created by zoid23 on 23/05/17.
 */

public class CoreActivity extends AppCompatActivity implements VolleyUi  {

    private static final String TAG = "ACTIVITY_VOLLEY";

    /** The {@link RequestQueue} */
    RequestQueue requestsQueue;

    /**
     * Add the {@link Request} at the {@link #requestsQueue}
     *
     * @param request the request to add
     * @return the request passed as a param
     */
    public Request bindToRequest (Request request){
        if(requestsQueue == null)
            requestsQueue = Volley.newRequestQueue(this);

        request.setTag(TAG);
        requestsQueue.add(request);
        return request;
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancellAllRequests();
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
     * Called on {@link #onStop()}
     */
    public void cancellAllRequests (){
        if(requestsQueue != null)
            requestsQueue.cancelAll(TAG);
    }

}

