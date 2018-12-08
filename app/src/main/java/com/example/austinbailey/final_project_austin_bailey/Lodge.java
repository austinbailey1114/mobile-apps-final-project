package com.example.austinbailey.final_project_austin_bailey;

import com.google.android.gms.maps.model.LatLng;

public class Lodge {

    public LatLng location;
    public String name;
    public double rating;
    public String address;

    public Lodge(Double latitude, Double longitude, String name, Double rating, String address) {
        this.location = new LatLng(latitude, longitude);
        this.name = name;
        this.rating = rating;
        this.address = address;
    }
}
