package mapswebservice.cris_.mapsexample.Parsers;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mapswebservice.cris_.mapsexample.Logic.Place;

/**
 * Created by cris_ on 1/03/2017.
 */

public class PlaceJSONParser {

    public static List<Place> parser(String data){

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(data);
            List<Place> placeList = new ArrayList<>();

            for ( int i = 0; i < jsonArray.length(); i++ ){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Place place = new Place();
                place.setId(jsonObject.getInt("id_lugar"));
                place.setName(jsonObject.getString("nombre"));
                place.setDesc(jsonObject.getString("descripcion"));

                double lon = jsonObject.getDouble("longitud");
                double lat = jsonObject.getDouble("latitud");

                place.setLocation(new LatLng(lat,lon));

                placeList.add(place);
            }
            return placeList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
