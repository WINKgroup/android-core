package by.wink.core.rest;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;

import java.util.Map;

import by.wink.core.rest.multipart.FileData;
import by.wink.core.rest.multipart.MultipartRequest;

public class CoreStringMultipartRequest extends MultipartRequest {

    private CoreResponseListener<String> listener;

    public CoreStringMultipartRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Map<String, FileData> multipart, CoreResponseListener<String> listener) {
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
        listener.onResponse(new String(response.data));
    }
}
