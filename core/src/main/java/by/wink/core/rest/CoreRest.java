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

    protected enum Method {
        GET(Request.Method.GET),
        POST(Request.Method.POST),
        PUT(Request.Method.PUT),
        HEAD(Request.Method.HEAD),
        PATCH(Request.Method.PATCH),
        TRACE(Request.Method.TRACE),
        DELETE(Request.Method.DELETE);

        private final int value;
        Method(int value) {
            this.value = value;
        }
    }
    private VolleyUi view;

    public CoreRest (VolleyUi view){
        this.view = view;
    }

    protected final void getText(@NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringRequest(Method.GET.value, url, mergeHeaders(headers), (String) null, listener));
    }

    protected final void requestText(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringRequest(method.value, url, mergeHeaders(headers), params, listener));
    }

    protected final void requestText(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable String body, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringRequest(method.value, url, mergeHeaders(headers), body, listener));
    }

    protected final void getJsonObject(@NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectRequest(Method.GET.value, url, mergeHeaders(headers), (String) null, listener));
    }

    protected final void requestJSONObject(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectRequest(method.value, url, mergeHeaders(headers), params, listener));
    }

    protected final void requestJSONObject(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable String body, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectRequest(method.value, url, mergeHeaders(headers), body, listener));
    }

    protected final void getJsonArray(@NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayRequest(Method.GET.value, url, mergeHeaders(headers), (String) null, listener));
    }

    protected final void requestJsonArray(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayRequest(method.value, url, mergeHeaders(headers), params, listener));
    }

    protected final void requestJsonArray(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable String body, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayRequest(method.value, url, mergeHeaders(headers), body, listener));
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

    private Map<String, String> mergeHeaders (@Nullable Map<String, String> headers){
        Map<String, String> result = getDefaultHeaders();
        if(headers != null)
            result.putAll(headers);
        return result;
    }

    private Request request (Request request){
        request.setRetryPolicy(getRetryPolicy());
        return view.bindToRequest(request);
    }

}
