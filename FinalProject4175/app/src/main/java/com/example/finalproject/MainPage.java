package com.example.finalproject;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        final Button btnMap = (Button) findViewById(R.id.btnMap);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainPage.this, MapsActivity.class);
                startActivity(intent5);
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_events:
                        Intent intent = new Intent(MainPage.this, eventActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_home:

                        break;
                    case R.id.navigation_account:
                        Intent intent3 = new Intent(MainPage.this, accountActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_group:
                        Intent intent4 = new Intent(MainPage.this, groupActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });


    }
}
