package winkit.android.core.rest;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;

import java.util.Map;

/**
 * The listener used for the {@link CoreRest} requests
 *
 * @param <T> the response type
 *
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreResponseListener<T> implements Response.Listener<T> {

    /** The result headers */
    private Map<String, String> resHeaders;

    /**
     * Return an {@link Response.ErrorListener}
     *
     * @return The error listener
     */
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

    /**
     * Called on request success
     *
     * @param response The request response
     * @throws JSONException Util fo to translate the models from jsons, if throw the call go to {@link #onError(VolleyError)}
     */
    public abstract void onSuccess (T response) throws JSONException;

    /**
     * Called on request error
     *
     * @param error The request error
     */
    public abstract void onError (VolleyError  error);

    /**
     * Return the request response headers
     *
     * @return request headers
     */
    public Map<String, String> getResultHeaders() {
        return resHeaders;
    }

    void setResHeaders(Map<String, String> resHeaders) {
        this.resHeaders = resHeaders;
    }
}
