package com.example.austinbailey.final_project_austin_bailey;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

public class MyOnClickListener implements View.OnClickListener {
    Context context;
    public TextView titleTextView, lat, lng, wiki;
    public MyOnClickListener(Context context) {
        this.context = context;

    }

    public void onClick(View v) {
        // Use MapsActivity from here

    }
}
