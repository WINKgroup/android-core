package by.wink.core.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import by.wink.core.rest.multipart.FileData;
import by.wink.core.rest.multipart.MultipartRequest;

public class CoreJSONObjectMultipartRequest extends MultipartRequest {

    private CoreResponseListener<JSONObject> listener;

    public CoreJSONObjectMultipartRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Map<String, FileData> multipart, CoreResponseListener<JSONObject> listener) {
        super(method, url, headers, params, multipart, listener.getErrorListener());
        this.listener = listener;
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        listener.setResHeaders(response.headers);
        return super.parseNetworkResponse(response);
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        try {
            listener.onResponse(new JSONObject(new String(response.data)));
        } catch (JSONException je) {
            listener.onError(new ParseError(je));
        }
    }

}
