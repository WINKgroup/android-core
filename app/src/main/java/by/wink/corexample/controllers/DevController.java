package by.wink.corexample.controllers;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import by.wink.core.CoreController;
import by.wink.core.rest.CoreDefaultErrorResponseListener;
import by.wink.core.uielements.VolleyUi;
import by.wink.corexample.models.Developer;
import by.wink.corexample.rest.ApiClient;

/**
 * Created by zoid23 on 24/05/17.
 */

public class DevController extends CoreController<ApiClient> {

    public DevController(VolleyUi view) {
        super(view, ApiClient.class);
    }


    public void getAll (String param, Integer param2, final ParsedResponse<ArrayList<Developer>> responseListener){
        rest.getDevs(new CoreDefaultErrorResponseListener<JSONArray>(responseListener) {
            @Override
            public void onSuccess(JSONArray response) throws JSONException {
                ArrayList<Developer> devs = new ArrayList<>();
                for(int i = 0; i<response.length(); i++)
                    devs.add(new Developer(response.getJSONObject(i)));

                responseListener.onSuccess(devs);
            }
        });
    }
}
