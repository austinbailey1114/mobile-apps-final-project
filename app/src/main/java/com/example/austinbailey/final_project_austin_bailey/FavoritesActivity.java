package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FavoritesActivity extends AppCompatActivity {

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "lodges").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("test", db.lodgeRoomDao().getAll().toString());
            }
        }) .start();
    }
}
