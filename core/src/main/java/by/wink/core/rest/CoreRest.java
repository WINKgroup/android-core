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

import by.wink.core.rest.multipart.FileData;
import by.wink.core.uielements.VolleyUi;

/**
 * Base class for all network requests.
 *
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreRest {

    /**
     * Supported request methods.
     */
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

    /** The joined {@link VolleyUi} view, all requests cancelled on {#onStop()} */
    private VolleyUi view;

    /**
     * Creates a new request with the given method (one of the values from {@link }),
     * It will be instantiate from the {@link by.wink.core.CoreController}, the subclass specified on generic type.
     */
    public CoreRest (VolleyUi view){
        this.view = view;
    }


    /**
     * Make a request to get a text response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param listener The text request listener
     */
    protected final void requestText(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringRequest(method.value, url, mergeHeaders(headers), (String) null, listener));
    }

    /**
     * Make a request to get a text response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param params The request params
     * @param listener The text request listener
     */
    protected final void requestText(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringRequest(method.value, url, mergeHeaders(headers), params, listener));
    }

    /**
     * Make a request to get a text response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param body The request body
     * @param listener The text request listener
     */
    protected final void requestText(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable String body, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringRequest(method.value, url, mergeHeaders(headers), body, listener));
    }

    /**
     * Make a request to get a text response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param params The request params
     * @param multipart The request multipart
     * @param listener The text request listener
     */
    protected final void requestText(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @Nullable Map<String, FileData> multipart, @NonNull CoreResponseListener<String> listener){
        request(new CoreStringMultipartRequest(method.value, url, mergeHeaders(headers), params, multipart, listener));
    }

    /**
     * Make a request to get a {@link JSONObject} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param listener The text request listener
     */
    protected final void requestJSONObject(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectRequest(method.value, url, mergeHeaders(headers), (String) null, listener));
    }

    /**
     * Make a request to get a {@link JSONObject} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param params The request params
     * @param listener The text request listener
     */
    protected final void requestJSONObject(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable JSONObject params, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectRequest(method.value, url, mergeHeaders(headers), params, listener));
    }

    /**
     * Make a request to get a {@link JSONObject} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param body The request body
     * @param listener The text request listener
     */
    protected final void requestJSONObject(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable String body, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectRequest(method.value, url, mergeHeaders(headers), body, listener));
    }

    /**
     * Make a request to get a {@link JSONObject} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param params The request params
     * @param multipart The request multipart
     * @param listener The text request listener
     */
    protected final void requestJSONObject(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @Nullable Map<String, FileData> multipart, @NonNull CoreResponseListener<JSONObject> listener){
        request(new CoreJSONObjectMultipartRequest(method.value, url, mergeHeaders(headers), params, multipart, listener));
    }

    /**
     * Make a request for to get a {@link JSONArray} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param listener The text request listener
     */
    protected final void requestJsonArray(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayRequest(method.value, url, mergeHeaders(headers), (String) null, listener));
    }

    /**
     * Make a request for to get a {@link JSONArray} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param params The request params
     * @param listener The text request listener
     */
    protected final void requestJsonArray(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable JSONArray params, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayRequest(method.value, url, mergeHeaders(headers), params, listener));
    }

    /**
     * Make a request for to get a {@link JSONArray} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param body The request body
     * @param listener The text request listener
     */
    protected final void requestJsonArray(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable String body, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayRequest(method.value, url, mergeHeaders(headers), body, listener));
    }

    /**
     * Make a request to get a {@link JSONArray} response
     *
     * @param method The request method as a {@link Method} enum
     * @param url The url
     * @param headers The request headers
     * @param params The request params
     * @param multipart The request multipart
     * @param listener The text request listener
     */
    protected final void requestJsonArray(@NonNull Method method, @NonNull String url, @Nullable final Map<String, String> headers, @Nullable Map<String, String> params, @Nullable Map<String, FileData> multipart, @NonNull CoreResponseListener<JSONArray> listener){
        request(new CoreJSONArrayMultipartRequest(method.value, url, mergeHeaders(headers), params, multipart, listener));
    }

    /**
     * Override it for define a set of defult headers used for all requests.
     *
     * @return the defaults headers
     */
    protected Map<String, String> getDefaultHeaders (){
        return new HashMap<>();
    }

    /**
     * Override it for define a {@link DefaultRetryPolicy} used for all requests.
     *
     * @return the defaults {@link DefaultRetryPolicy}
     */
    protected RetryPolicy getDefaultRetryPolicy (){
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
        request.setRetryPolicy(getDefaultRetryPolicy());
        return view.bindToRequest(request);
    }

}
