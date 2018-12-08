package com.example.austinbailey.final_project_austin_bailey;

import com.google.android.gms.maps.model.LatLng;

public class Lodge {

    public LatLng location;
    public String name;
    public double rating;
    public String address;

    public Lodge(String latitude, String longitude, String name, String rating, String address) {
        this.location = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        this.name = name;
        this.rating = Double.parseDouble(rating);
        this.address = address;
    }
}
