package mapswebservice.cris_.mapsexample;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import mapswebservice.cris_.mapsexample.Logic.Place;
import mapswebservice.cris_.mapsexample.Parsers.PlaceJSONParser;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Place> placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        beginComponents();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    private void beginComponents() {
        //LLENAR ARRAY
        if (isOnline()){
            //10.0.2.2
            pedirDatos("http://192.168.0.23/maps/consulta.php");
        }else{
            Toast.makeText(getApplicationContext(),"SIN CONEXION",Toast.LENGTH_SHORT).show();
        }
    }

    public void pedirDatos(String uri) {
        MyTask task = new MyTask();

        task.execute(uri);
    }


    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if ( networkInfo != null && networkInfo.isConnectedOrConnecting() )
            return true;
        else
            return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
//METODO QUE TRAER LOS LUGARES DE LA LISTA
        for (Place place:
             placeList) {
            mMap.addMarker(new MarkerOptions().position(place.getLocation()).title(place.getName()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeList.get(1).getLocation(),14));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
             //   Toast.makeText(getApplicationContext(),"Toco marker",Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(MapsActivity.this,Pop.class));

               //METODO QUE TRAE LA POS DE EL QUE CLIKCEO
                   Place placeAux = searchPlace(marker.getPosition().longitude,marker.getPosition().latitude);


                if (placeAux!=null){
                    Intent popIntent = new Intent(MapsActivity.this,Pop.class);
                    popIntent.putExtra("name",placeAux.getName());
                    popIntent.putExtra("desc",placeAux.getDesc());
                    startActivity(popIntent);
                }


                return false;
            }
        });
    }

    public Place searchPlace(double lon,double lat){

        for (Place place: placeList) {

            if (lon == place.getLocation().longitude && lat == place.getLocation().latitude){
                //Toast.makeText(getApplicationContext(),"entro if" ,Toast.LENGTH_SHORT).show();
                return place;
            }
        }
        return null;
    }


    private class MyTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String data = HttpMananger.getData(params[0]);
            return data;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Log.d("DATASS","msj: " + result.toString());
            placeList = PlaceJSONParser.parser(result);
            cargarDatos();
        }
    }

    public void cargarDatos() {
        if (!placeList.isEmpty()){
            for (Place place:placeList ) {
                Log.d("DATOS",place.getName() + ", POS: " + place.getLocation());
            }
        }
    }

}


