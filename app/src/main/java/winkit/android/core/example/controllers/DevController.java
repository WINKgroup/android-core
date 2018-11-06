package winkit.android.core.example.controllers;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import winkit.android.core.CoreController;
import winkit.android.core.rest.CoreDefaultErrorResponseListener;
import winkit.android.core.uielements.VolleyUi;
import winkit.android.core.example.models.Developer;
import winkit.android.core.example.rest.ApiClient;

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
