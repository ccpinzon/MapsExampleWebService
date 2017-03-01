package mapswebservice.cris_.mapsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button _btnOpenMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginComponents();
    }

    private void beginComponents() {
        _btnOpenMap = (Button) findViewById(R.id.btn_openMap);
        _btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(mapIntent);
            }
        });
    }
}
