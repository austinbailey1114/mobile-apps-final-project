package com.example.austinbailey.final_project_austin_bailey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ViewLodgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lodge);

        Lodge lodge = (Lodge) getIntent().getSerializableExtra("lodge");
        Log.i("MSG", lodge.name);
    }

    public void map(View v) {

    }

    public void save(View v) {

    }
}
