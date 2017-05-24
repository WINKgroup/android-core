package by.wink.corexample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import by.wink.core.CoreController;
import by.wink.core.uielements.CoreActivity;
import by.wink.corexample.controllers.DevController;
import by.wink.corexample.models.Developer;

public class MainActivity extends CoreActivity {

    private TextView content;
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
    }

    private void request (){
        content.setText("request ...");
        devController.getAll(new CoreController.ParsedResponse<ArrayList<Developer>>() {
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
    }
}
