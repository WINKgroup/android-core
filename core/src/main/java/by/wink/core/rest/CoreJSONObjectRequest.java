package by.wink.core.rest;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zoid23 on 23/05/17.
 */

class CoreJSONObjectRequest extends JsonObjectRequest {

    Map<String, String> headers;
    private String rawBody;
    private CoreResponseListener<JSONObject> listener;

    CoreJSONObjectRequest(int method, String url, Map<String, String> headers, @Nullable String rawBody, CoreResponseListener<JSONObject> listener) {
        this(method, url, headers, listener);
        this.rawBody = rawBody;
    }

    CoreJSONObjectRequest(int method, String url, Map<String, String> headers, @Nullable JSONObject body, CoreResponseListener<JSONObject> listener) {
        super(method, url, body, listener, listener.getErrorListener());
        this.headers = headers;
        this.listener = listener;
    }

    private CoreJSONObjectRequest (int method, String url, Map<String, String> headers, CoreResponseListener<JSONObject> listener){
        super(method, url, null, listener, listener.getErrorListener());
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public byte[] getBody() {
        if(rawBody != null)
            return rawBody.getBytes();
        return super.getBody();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        listener.setResHeaders(response.headers);
        return super.parseNetworkResponse(response);
    }
}
