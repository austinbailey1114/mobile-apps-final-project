package com.example.austinbailey.final_project_austin_bailey;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Lodge implements Serializable {

    public double latitude;
    public double longitude;
    public String name;
    public double rating;
    public String address;
    // optional LodgeRoom ID for deletion
    public int id = -1;

    public Lodge(Double latitude, Double longitude, String name, Double rating, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.rating = rating;
        this.address = address;
    }

    public Lodge(LodgeRoom lodgeRoom) {
        this.latitude = lodgeRoom.lat;
        this.longitude = lodgeRoom.lng;
        this.rating = lodgeRoom.rating;
        this.address = lodgeRoom.address;
        this.name = lodgeRoom.name;
        this.id = lodgeRoom.id;
    }
}
