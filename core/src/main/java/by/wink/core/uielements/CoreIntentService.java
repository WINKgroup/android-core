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

    RequestQueue requestsQueue;

    public CoreIntentService(String name) {
        super(name);
    }

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

    @Override
    public Context getContext() {
        return this;
    }

    public void cancellAllRequests (){
        if(requestsQueue != null)
            requestsQueue.cancelAll(TAG);
    }
}
