package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.favorites:
                intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
