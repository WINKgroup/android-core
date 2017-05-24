package by.wink.core.rest;

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
    private CoreResponseListener listener;

    public CoreJSONObjectRequest(int method, String url, Map<String, String> headers, JSONObject jsonRequest, CoreResponseListener listener) {
        super(method, url, jsonRequest, listener, listener.getErrorListener());
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        listener.setResHeaders(response.headers);
        return super.parseNetworkResponse(response);
    }
}
