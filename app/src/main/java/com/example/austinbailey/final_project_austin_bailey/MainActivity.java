package com.example.austinbailey.final_project_austin_bailey;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // URL to hit for places in Brighton, MA
    private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.345800,-71.151250&radius=500&type=lodging&key=AIzaSyAXQ2fni7xMXDm-u9U9OmdLqd46beqDe18";
    // Hold context
    final Context context = this;
    // ArrayList for recycler view
    private ArrayList<Lodge> lodgeList = new ArrayList<>();
    // Recycler View components
    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private class HttpTask extends AsyncTask<URL, Integer, Long> {
        public JSONObject results;

        protected Long doInBackground(URL... urls) {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                String results = response.get("results").toString();
                                JSONArray lodges = new JSONArray(results);
                                for (int i = 0; i < lodges.length(); i++) {
                                    // Get location
                                    JSONObject lodge = new JSONObject(lodges.get(i).toString());
                                    JSONObject lodgeGeometry = new JSONObject(lodge.getString("geometry"));
                                    JSONObject lodgeLocation = new JSONObject(lodgeGeometry.getString("location"));
                                    Double lat = lodgeLocation.getDouble("lat");
                                    Double lng = lodgeLocation.getDouble("lng");

                                    // Get name
                                    String name = lodge.getString("name");

                                    // Get rating
                                    Double rating = lodge.getDouble("rating");

                                    // Get address
                                    String address = lodge.getString("vicinity");

                                    Lodge newLodge = new Lodge(lat, lng, name, rating, address);
                                    lodgeList.add(newLodge);
                                }
                                Log.d("MSG", lodgeList.toString());
                            } catch (JSONException e) {
                                Log.i("MSG", "JSON Object exception");
                            }
                            recyclerView.setHasFixedSize(true);

                            // use a linear layout manager
                            mLayoutManager = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(mLayoutManager);

                            // specify an adapter (see also next example)
                            mAdapter = new MyAdapter(lodgeList, context);
                            recyclerView.setAdapter(mAdapter);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Log.i("MSG", "error");
                        }
                    });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(jsonObjectRequest);
            return (long) 5;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        new HttpTask().execute();
    }
}
