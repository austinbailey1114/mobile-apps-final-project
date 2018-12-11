package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private AppDatabase db;
    private ArrayList<Lodge> lodgeList = new ArrayList<>();
    // Recycler View components
    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "lodges").build();

        this.recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Convert to array readable by myAdapter
                List<LodgeRoom> lodgeRooms = db.lodgeRoomDao().getAll();
                for(int i = 0; i < lodgeRooms.size(); i++) {
                    lodgeList.add(lodgeRooms.get(i).copyToLodge());
                }
                mAdapter = new MyAdapter(lodgeList, context);
                recyclerView.setAdapter(mAdapter);
            }
        }) .start();
    }
}
