package by.wink.core.uielements;

import android.content.Context;

import com.android.volley.Request;

/**
 * A interface with for to implement the RequestQueue of the currents {@link by.wink.core.rest.CoreRest} requests
 *
 * Created by zoid23 on 23/05/17.
 */

public interface VolleyUi {

    /**
     * Add the {@link Request} at the {@link com.android.volley.RequestQueue}
     *
     * @param request the request to add
     * @return the request passed as a param
     */
    Request bindToRequest (Request request);

    /**
     * Cancel all volley request started from {@link by.wink.core.CoreController} connected whit this {@link VolleyUi} instance.
     */
    void cancellAllRequests ();

    /**
     * Return the current {@link Context}
     * @return context
     */
    Context getContext();
}
