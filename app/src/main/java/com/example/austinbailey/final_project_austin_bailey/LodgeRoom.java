package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class LodgeRoom {
    public LodgeRoom() {

    }

    public LodgeRoom(Lodge lodge) {
        this.name = lodge.name;
        this.lat = lodge.latitude;
        this.lng = lodge.longitude;
        this.rating = lodge.rating;
        this.address = lodge.address;
    }

    public Lodge copyToLodge() {
        return new Lodge(this);
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "lat")
    public double lat;

    @ColumnInfo(name = "lng")
    public double lng;

    @ColumnInfo(name = "rating")
    public double rating;

    @ColumnInfo(name = "address")
    public String address;
}
