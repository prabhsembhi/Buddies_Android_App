package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class detailOfEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_event);

        // get intent
        Intent intent = getIntent();

        String selectedItemName = intent.getStringExtra("selectedItem");
        Toast.makeText(this, ""+selectedItemName, Toast.LENGTH_SHORT).show();
    }
}
