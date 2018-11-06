package winkit.android.core.rest;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by zoid23 on 24/05/17.
 */

public class CoreStringRequest  extends StringRequest {

    private Map<String, String> headers, params;
    private String rawBody;
    private CoreResponseListener<String> listener;

    CoreStringRequest(int method, String url, Map<String, String> headers, @Nullable Map<String, String> params, CoreResponseListener<String> listener) {
        this(method, url, headers, listener);
        this.params = params;
    }

    CoreStringRequest(int method, String url, Map<String, String> headers, @Nullable String rawBody, CoreResponseListener<String> listener) {
        this(method, url, headers, listener);
        this.rawBody = rawBody;
    }

    private CoreStringRequest (int method, String url, Map<String, String> headers, CoreResponseListener<String> listener){
        super(method, url, listener, listener.getErrorListener());
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if(params != null)
            return params;
        return super.getParams();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if(rawBody != null)
            return rawBody.getBytes();
        return super.getBody();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        listener.setResHeaders(response.headers);
        return super.parseNetworkResponse(response);
    }
}
