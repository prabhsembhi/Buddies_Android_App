package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        final EditText adminId = findViewById(R.id.adminId);
        final EditText adminPw = findViewById(R.id.adminPass);
        final Button adminLogin = (Button) findViewById(R.id.adminLogin);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminId.getText().toString().equals("admin") && adminPw.getText().toString().equals("admin")) {
                    Intent intent2 = new Intent(adminLogin.this, adminPage.class);
                    startActivity(intent2);
                }
                else {
                    Toast.makeText(adminLogin.this, "Faild to login! Try again!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
