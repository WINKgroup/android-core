package by.wink.corexample.rest;

import org.json.JSONArray;

import by.wink.core.rest.CoreResponseListener;
import by.wink.core.rest.CoreRest;
import by.wink.core.uielements.VolleyUi;

/**
 * Created by zoid23 on 24/05/17.
 */

public class ApiClient extends CoreRest {

    private static final String BASE_URL = "http://www.mocky.io/v2/";

    public ApiClient(VolleyUi view) {
        super(view);
    }

    public void getDevs (CoreResponseListener<JSONArray> responseListener){
        getJsonArray(BASE_URL+"592572c21200005100686f0b", null, responseListener);
    }
}
