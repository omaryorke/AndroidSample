package com.tests.omar.androidsample.entities;

/**
 * Created by omar on 19/05/2015.
 */
public class Info {

    private String magnitude;
    private String place;
    private String time;
    private String coordinates;

    public Info(String _magnitude, String _place,String _time, String _coordinates){
        this.magnitude = _magnitude;
        this.place = _place;
        this.time = _time;
        this.coordinates = _coordinates;
    }


    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
