package winkit.android.core.example.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import winkit.android.core.CoreController;
import winkit.android.core.rest.CoreDefaultErrorResponseListener;
import winkit.android.core.uielements.VolleyUi;
import winkit.android.core.example.rest.UploadApiClient;

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
