package com.example.austinbailey.final_project_austin_bailey;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.345800,-71.151250&radius=500&type=lodging&key=AIzaSyAXQ2fni7xMXDm-u9U9OmdLqd46beqDe18";
    final Context context = this;

    private class HttpTask extends AsyncTask<URL, Integer, Long> {
        public JSONObject results;

        protected Long doInBackground(URL... urls) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("MSG", response.toString());
                            try {
                                String results = response.get("results").toString();
                                Log.i("MSG", results);
                            } catch (JSONException e) {

                            }
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
        new HttpTask().execute();
    }
}
