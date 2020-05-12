package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class manageGroup extends AppCompatActivity {
    int logo = R.drawable.group;
    String emailofuser;
    final String url = "http://10.0.2.2:3003/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);

        // get useremail
        GoogleSignInAccount GOOGLE = login.getvalue();
        final String useremail = GOOGLE.getEmail();

        final RecyclerView recyclerView_event = (RecyclerView)findViewById(R.id.recyclerView_usergroup);
        final Button btnBack1 = (Button) findViewById(R.id.backToAccount1);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(manageGroup.this, accountActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface ri = retrofit.create(RetrofitInterface.class);


        Call<List<Groups>> call2 = ri.getGroupByEamil(useremail);
        call2.enqueue(new Callback<List<Groups>>() {
            @Override
            public void onResponse(Call<List<Groups>> call, Response<List<Groups>> response) {

                if(!response.isSuccessful()) {
                    Toast.makeText(manageGroup.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Groups> groups = response.body();

                final CustomAdapter3 customAdapter3 = new CustomAdapter3(groups,logo);

                recyclerView_event.setAdapter(customAdapter3);
                GridLayoutManager layoutManager = new GridLayoutManager(manageGroup.this,1);
                recyclerView_event.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Groups>> call, Throwable t) {

            }
        });
    }
}
