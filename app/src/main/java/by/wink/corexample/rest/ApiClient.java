package by.wink.corexample.rest;

import com.android.volley.RetryPolicy;

import org.json.JSONArray;

import java.util.Map;

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

    @Override
    protected Map<String, String> getDefaultHeaders() {
        return super.getDefaultHeaders();
    }

    @Override
    protected RetryPolicy getDefaultRetryPolicy() {
        return super.getDefaultRetryPolicy();
    }

    public void getDevs (CoreResponseListener<JSONArray> responseListener){
        requestJsonArray(Method.GET, BASE_URL+"592572c21200005100686f0b", null, (String) null, responseListener);
    }

}
