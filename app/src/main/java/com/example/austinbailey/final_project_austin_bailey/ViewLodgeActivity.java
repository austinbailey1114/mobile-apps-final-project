package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewLodgeActivity extends AppCompatActivity {

    private Lodge lodge;
    private TextView name, rating, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lodge);

        // Get the lodge selected in the recycler view
        this.lodge = (Lodge) getIntent().getSerializableExtra("lodge");
        boolean isFavorites = getIntent().getBooleanExtra("isFavorites", false);
        Button button;
        // Hide the button depending on if this was loaded from Favorites
        if (isFavorites) {
            button = findViewById(R.id.save);
        } else {
            button = findViewById(R.id.delete);
        }
        button.setVisibility(View.INVISIBLE);

        // Grab text views
        this.name = findViewById(R.id.name);
        this.rating = findViewById(R.id.rating);
        this.address = findViewById(R.id.address);

        // Set text views
        this.name.setText(this.lodge.name);
        this.rating.setText("Rating: " + Double.toString(this.lodge.rating));
        this.address.setText(this.lodge.address);
    }

    public void map(View v) {
        // Open map
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("lat", this.lodge.latitude);
        intent.putExtra("lng", this.lodge.longitude);
        intent.putExtra("name", this.lodge.name);
        startActivity(intent);
    }

    public void save(View v) {
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "lodges").build();
        final LodgeRoom lodgeRoom = new LodgeRoom(this.lodge);
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.lodgeRoomDao().insertOne(lodgeRoom);
            }
        }) .start();
    }

    public void delete(View v) {
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "lodges").build();
        final int id = this.lodge.id;
        final Context context = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.lodgeRoomDao().deleteLodge(id);
                Intent intent = new Intent(context, FavoritesActivity.class);
                startActivity(intent);
            }
        }) .start();
    }
}
