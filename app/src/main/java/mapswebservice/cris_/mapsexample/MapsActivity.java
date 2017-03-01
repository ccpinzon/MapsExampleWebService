package mapswebservice.cris_.mapsexample;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import mapswebservice.cris_.mapsexample.Logic.Place;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        beginComponents();
        loadPlaces();
    }

    private void loadPlaces() {
        places.add(0,new Place(1,"UPTC","La Universidad Pedagógica y Tecnológica de Colombia (UPTC) es una universidad pública, estatal de carácter nacional, financiada principalmente por el Estado Colombiano,acreditada en alta calidad multicampus,con sede principal en la ciudad de Tunja. Es la Universidad más importante del departamento de Boyacá y una de las más prestigiosas en el Estado Colombiano por su nivel académico haciendo presencia en 8 departamentos del país."
                ,  new LatLng(5.704516 ,-72.941687)));
        places.add(0,new Place(2,"Parque De La Villa","Dado que no se conocen documentos referentes a las condiciones de la fundación de la ciudad, se puede afirmar que Sogamoso no tuvo un fundador particular.",
                new LatLng(5.714519,-72.927337)));
        places.add(0,new Place(3,"Museo","Creado por el arqueólogo Eliécer Silva Célis, en 1942, sobre los restos de un cementerio muisca, en el sector de Los Solares (hoy Barrio El Sol), en la ciudad de Sogamoso, Colombia.\n" +
                "\n" +
                "Por disposición oficial se le dio el nombre de su fundador en el año 2008.",
                new LatLng(5.709544,-72.923765)));
    }

    private void beginComponents() {
        places = new ArrayList<Place>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        for (Place place:
             places) {
            mMap.addMarker(new MarkerOptions().position(place.getLocation()).title(place.getName()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(places.get(1).getLocation(),14));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Toast.makeText(getApplicationContext(),"Toco marker",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MapsActivity.this,Pop.class));
                return false;
            }
        });
    }
}


