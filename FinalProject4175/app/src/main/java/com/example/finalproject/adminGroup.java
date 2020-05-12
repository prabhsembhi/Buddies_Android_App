package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class adminGroup extends AppCompatActivity {
    int logo = R.drawable.group;
    String emailofuser;
    final String url = "http://10.0.2.2:3003/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_group);
        final RecyclerView recyclerView_event = (RecyclerView)findViewById(R.id.adminRec1);
        final Button btnBack1 = (Button) findViewById(R.id.btnback);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminGroup.this, adminPage.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface ri = retrofit.create(RetrofitInterface.class);


        Call<List<Groups>> call2 = ri.getGroups();
        call2.enqueue(new Callback<List<Groups>>() {
            @Override
            public void onResponse(Call<List<Groups>> call, Response<List<Groups>> response) {

                if(!response.isSuccessful()) {

                }
                List<Groups> groups = response.body();

                final CustomAdapter3 customAdapter3 = new CustomAdapter3(groups,logo);

                recyclerView_event.setAdapter(customAdapter3);
                GridLayoutManager layoutManager = new GridLayoutManager(adminGroup.this,1);
                recyclerView_event.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Groups>> call, Throwable t) {

            }
        });
    }
}
