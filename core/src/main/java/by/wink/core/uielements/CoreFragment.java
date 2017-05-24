package by.wink.core.uielements;

import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zoid23 on 23/05/17.
 */

public class CoreFragment extends Fragment implements VolleyUi {
    private static final String TAG = "FRAGMENT_VOLLEY";
    RequestQueue requestsQueue;

    public Request bindToRequest (Request request){
        if(requestsQueue == null)
            requestsQueue = Volley.newRequestQueue(getContext());

        request.setTag(TAG);
        requestsQueue.add(request);
        return request;
    }

    @Override
    public void onStop() {
        super.onStop();
        cancellAllRequests();
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