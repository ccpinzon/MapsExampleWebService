package mapswebservice.cris_.mapsexample;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mapswebservice.cris_.mapsexample.Logic.Place;
import mapswebservice.cris_.mapsexample.Parsers.PlaceJSONParser;

public class MainActivity extends AppCompatActivity {

    private Button _btnOpenMap;
    private Button _btnTestWebService;
    private TextView _textViewTest;

    List<Place> placeList;

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

        _textViewTest = (TextView) findViewById(R.id.txtTest);
        _btnOpenMap = (Button) findViewById(R.id.btn_testWebService);
        _btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()){
                    //10.0.2.2
                    pedirDatos("http://192.168.0.23/maps/consulta.php");
                }else {
                    Toast.makeText(getApplicationContext(),"SIN CONEXION",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void pedirDatos(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }
    public void cargarDatos(){
        if (!placeList.isEmpty()){
            for ( Place place:placeList ) {
                _textViewTest.append(place.getName() + "\n");
            }
        }
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if ( networkInfo != null && networkInfo.isConnectedOrConnecting() )
            return true;
        else
            return false;
    }

    public class MyTask extends AsyncTask<String,String,String >{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"SE EJECUTO TASK",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String data = HttpMananger.getData(params[0]);
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            placeList = PlaceJSONParser.parser(result);
            cargarDatos();
            Toast.makeText(getApplicationContext(),"SE FINALIZO TASK",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }
    }

}
