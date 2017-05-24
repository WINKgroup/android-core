package by.wink.core.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Map;

/**
 * Created by zoid23 on 23/05/17.
 */

class CoreJSONArrayRequest extends JsonArrayRequest {

    Map<String, String> headers;
    private CoreResponseListener listener;

    public CoreJSONArrayRequest(int method, String url, Map<String, String> headers, JSONArray jsonRequest, CoreResponseListener listener) {
        super(method, url, jsonRequest, listener, listener.getErrorListener());
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        listener.setResHeaders(response.headers);
        return super.parseNetworkResponse(response);
    }
}
