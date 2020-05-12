package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class adminUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        final TextView tv = findViewById(R.id.tvUser);
        final Button btn = findViewById(R.id.btnGoBackToAdmin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3003/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<User>> call = ri.getUsers();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                final List<User> userlist = response.body();

                for(User u : userlist) {

                    String userinfo = "User Email: " + u.getUserEmail().toString() + "\nUser Name: " + u.getFullName().toString()+"\n\n";
                    tv.append(userinfo);


                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(adminUser.this, adminPage.class);
                startActivity(intent1);
            }
        });

    }
}
