package winkit.android.core.example;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import winkit.android.core.CoreController;
import winkit.android.core.MultipleCoreRequests;
import winkit.android.core.MultipleCoreRequests.CtrlRequest;
import winkit.android.core.example.controllers.DevController;
import winkit.android.core.uielements.CoreActivity;
import winkit.android.core.uielements.CoreImageLoader;
import winkit.android.core.example.models.Developer;
import winkit.android.core.example.controllers.UploadController;

public class MainActivity extends CoreActivity {

    private static final String TAG = "MainActivity";
    private static final int PICK_IMAGE = 123;
    private TextView content;
    private ImageView uploadedImage;
    private UploadController uploadController = new UploadController(this);
    private DevController devController = new DevController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (TextView) findViewById(R.id.content_text);
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
        request ();
        uploadedImage = (ImageView) findViewById(R.id.uploaded_image);

        findViewById(R.id.upload_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void request (){
        content.setText("request ...");
        devController.getAll("", 2, new CoreController.ParsedResponse<ArrayList<Developer>>() {
            @Override
            public void onSuccess(ArrayList<Developer> developers) {
                String result = "";
                for(Developer dev: developers)
                    result += dev.getName() + "\n";
                content.setText(result);
            }

            @Override
            public void onError(String error) {
                content.setText(error);
            }
        });

        final CtrlRequest<ArrayList<Developer>> fRequest = new CtrlRequest<>(devController, "getAll", "", 0);
        final CtrlRequest<ArrayList<Developer>> sRequest = new CtrlRequest<>(devController, "getAll", "", 1);
        new MultipleCoreRequests(fRequest, sRequest).start(new MultipleCoreRequests.MultipleCoreRequestsResponse() {
            @Override
            public void onAllFinished(boolean allSuccess) {
                fRequest.getResult().get(0).getName();
            }
        });

    }

    private void selectImage () {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            uploadController.upload(new File(picturePath), new CoreController.ParsedResponse<String>() {
                @Override
                public void onSuccess(String s) {
                    Log.d(TAG, s);
                    CoreImageLoader.load(s).withDefault(R.mipmap.ic_launcher_round).animated(android.R.anim.fade_out).into(uploadedImage);
                }

                @Override
                public void onError(String error) {
                    Log.e(TAG, error);
                }
            });
        }
    }
}
