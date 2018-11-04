package by.wink.corexample.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import by.wink.core.CoreController;
import by.wink.core.rest.CoreDefaultErrorResponseListener;
import by.wink.core.uielements.VolleyUi;
import by.wink.corexample.rest.UploadApiClient;

public class UploadController extends CoreController<UploadApiClient> {

    public UploadController(VolleyUi view) {
        super(view, UploadApiClient.class);
    }

    public void upload (File f, final ParsedResponse<String> res){
        rest.postImage(f, new CoreDefaultErrorResponseListener<JSONObject>(res) {
            @Override
            public void onSuccess(JSONObject response) throws JSONException {
                res.onSuccess(UploadApiClient.BASE_URL + response.getString("fileUrl"));
            }
        });
    }
}
