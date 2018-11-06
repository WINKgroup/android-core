package winkit.android.core.example.rest;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import winkit.android.core.rest.CoreResponseListener;
import winkit.android.core.rest.CoreRest;
import winkit.android.core.rest.multipart.FileData;
import winkit.android.core.uielements.VolleyUi;

public class UploadApiClient extends CoreRest {

    public static final String BASE_URL = "http://306b40c9.ngrok.io";

    public UploadApiClient(VolleyUi view) {
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

    public void postImage (File file, CoreResponseListener<JSONObject> responseListener){
        Map<String, FileData> files = new HashMap<>();
        Map<String, String> body = new HashMap<>();
        body.put("foo", "bar");
        try {
            files.put("image", new FileData("imageLabel", file, "image/jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
            responseListener.onError(new VolleyError(e.getMessage()));
        }
        requestJSONObject(Method.POST, BASE_URL+"/fileUpload?foo=bar", null, body, files, responseListener);
    }
}
