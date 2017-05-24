package by.wink.core.uielements;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zoid23 on 23/05/17.
 */

public class CoreActivity extends AppCompatActivity implements VolleyUi  {

    private static final String TAG = "ACTIVITY_VOLLEY";
    RequestQueue requestsQueue;

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

