package by.wink.core.rest;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;

import java.util.Map;

/**
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreResponseListener<T> implements Response.Listener<T> {

    private Map<String, String> resHeaders;

    Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error);
            }
        };
    }

    @Override
    public final void onResponse(T response) {
        try {
            onSuccess(response);
        } catch (JSONException ex){
            onError(new VolleyError("JSON Exception error", ex));
        }
    }

    public abstract void onSuccess (T response) throws JSONException;


    public abstract void onError (VolleyError  error);

    public Map<String, String> getResultHeaders() {
        return resHeaders;
    }

    void setResHeaders(Map<String, String> resHeaders) {
        this.resHeaders = resHeaders;
    }
}
