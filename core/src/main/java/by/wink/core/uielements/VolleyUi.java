package by.wink.core.uielements;

import android.content.Context;

import com.android.volley.Request;

/**
 * Created by zoid23 on 23/05/17.
 */

public interface VolleyUi {
    Request bindToRequest (Request request);
    void cancellAllRequests ();
    Context getContext();
}
