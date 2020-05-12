package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Button btnGroup = (Button) findViewById(R.id.ManageGroups);
        Button btnUser = (Button) findViewById(R.id.ManageUsers);
        Button btnSignout = (Button) findViewById(R.id.adminSignOut);

        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(adminPage.this, adminGroup.class);
                startActivity(intent2);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(adminPage.this, adminUser.class);
                startActivity(intent1);
            }
        });
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(adminPage.this, adminGroup.class);
                startActivity(intent3);
            }
        });
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(adminPage.this, login.class);
                startActivity(intent4);
            }
        });


    }
}
