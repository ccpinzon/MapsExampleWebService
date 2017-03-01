package mapswebservice.cris_.mapsexample.Logic;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by cris_ on 28/02/2017.
 */

public class Place {
    private int id;
    private String name;
    private String desc;
    private LatLng location;

    public Place(int id, String name, String desc, LatLng location) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
