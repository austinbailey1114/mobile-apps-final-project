package com.example.austinbailey.final_project_austin_bailey;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class ViewLodgeActivity extends AppCompatActivity {

    private Lodge lodge;
    private TextView name, rating, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lodge);

        // Get the lodge selected in the recycler view
        this.lodge = (Lodge) getIntent().getSerializableExtra("lodge");
        final Lodge lodgeCopy = lodge;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://maps.googleapis.com/maps/api/place/photo?maxwidth=650&photoreference=" + lodgeCopy.photoReference + "&key=AIzaSyAXQ2fni7xMXDm-u9U9OmdLqd46beqDe18");
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Log.i("MSG", url.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageView imageView = findViewById(R.id.imageView);
                            imageView.setImageBitmap(bmp);
                        }
                    });

                } catch (Exception e) {
                    Log.i("MSG", e.toString());
                }
            }
        }).start();

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

        Log.i("MSG", lodge.photoReference);
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

        Toast toast = Toast.makeText(this, "Hotel Added to Favorites", Toast.LENGTH_SHORT);
        toast.show();
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

        Toast toast = Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void browser(View v) {
        String uri = "https://www.google.com/maps/search/?api=1&query=Google&query_place_id=" + this.lodge.googleID;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
}
