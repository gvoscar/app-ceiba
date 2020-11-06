package co.com.ceiba.mobile.pruebadeingreso.pojos;

import java.io.Serializable;

import io.realm.RealmObject;

public class Geo extends RealmObject implements Serializable {
    private String lat;
    private String lng;

    public Geo() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "lat: " + lat + ", lng: " + lng;
    }
}
