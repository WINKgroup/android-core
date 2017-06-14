package by.wink.core.rest;

import com.android.volley.VolleyError;

import org.json.JSONException;

import by.wink.core.CoreController;

/**
 * The listener used for the {@link CoreRest} requests.
 * This class implement the onError method from {@link CoreResponseListener}
 * passing to the {@link by.wink.core.CoreController.ParsedResponse} the localizedErrorMessage.
 *
 * @param <T> the response type
 *
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreDefaultErrorResponseListener<T> extends CoreResponseListener<T> {

    private CoreController.ParsedResponse response;

    /**
     * Constructor
     *
     * @param response the {@link by.wink.core.CoreController.ParsedResponse} response
     */
    public CoreDefaultErrorResponseListener (CoreController.ParsedResponse response){
        this.response = response;
    }

    @Override
    public void onError(VolleyError error) {
        String errorMess = error.getLocalizedMessage();
        if(errorMess == null) errorMess = error.getMessage();
        if(errorMess == null) errorMess = "Connection error ... ";
        response.onError(errorMess);
    }
}
