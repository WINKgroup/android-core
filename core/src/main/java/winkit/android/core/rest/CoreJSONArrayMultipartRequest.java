package winkit.android.core.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Map;

import winkit.android.core.rest.multipart.FileData;
import winkit.android.core.rest.multipart.MultipartRequest;

public class CoreJSONArrayMultipartRequest extends MultipartRequest {

    private CoreResponseListener<JSONArray> listener;

    public CoreJSONArrayMultipartRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Map<String, FileData> multipart, CoreResponseListener<JSONArray> listener) {
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
            listener.onResponse(new JSONArray(new String(response.data)));
        } catch (JSONException je) {
            listener.onError(new ParseError(je));
        }
    }

}