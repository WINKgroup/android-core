package by.wink.core.rest;

import com.android.volley.VolleyError;

import org.json.JSONException;

import by.wink.core.CoreController;

/**
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreDefaultErrorResponseListener<T> extends CoreResponseListener<T> {

    private CoreController.ParsedResponse response;

    public CoreDefaultErrorResponseListener (CoreController.ParsedResponse response){
        this.response = response;
    }

    @Override
    public void onError(VolleyError error) {
        response.onError(error.getLocalizedMessage());
    }
}
