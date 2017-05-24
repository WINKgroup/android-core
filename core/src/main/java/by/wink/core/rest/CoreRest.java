package by.wink.core.rest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import by.wink.core.uielements.VolleyUi;

/**
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreRest {

    private VolleyUi view;

    public CoreRest (VolleyUi view){
        this.view = view;
    }

    protected final void getJSONObject (@NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener listener){
        request(new CoreJSONObjectRequest(Request.Method.GET, url, mergeHeaders(headers), null, listener));
    }

    protected final void postJsonObject(@NonNull String url, @Nullable final Map<String, String> headers, @Nullable JSONObject parmas, @NonNull CoreResponseListener listener){
        request(new CoreJSONObjectRequest(Request.Method.POST, url, mergeHeaders(headers), parmas, listener));
    }

    protected final void getJSONArray (@NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener listener){
        request(new CoreJSONArrayRequest(Request.Method.GET, url, mergeHeaders(headers), null, listener));
    }

    protected final void postJsonArray(@NonNull String url, @Nullable final Map<String, String> headers, @Nullable JSONArray parmas, @NonNull CoreResponseListener listener){
        request(new CoreJSONArrayRequest(Request.Method.POST, url, mergeHeaders(headers), parmas, listener));
    }

    protected Map<String, String> getDefaultHeaders (){
        return new HashMap<>();
    }

    protected RetryPolicy getRetryPolicy (){
        return new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT );
    }

    private Map<String, String> mergeHeaders (Map<String, String> headers){
        Map<String, String> result = getDefaultHeaders();
        result.putAll(headers);
        return result;
    }

    private Request request (Request request){
        request.setRetryPolicy(getRetryPolicy());
        return view.bindToRequest(request);
    }

}
