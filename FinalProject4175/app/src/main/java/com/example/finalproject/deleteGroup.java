package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class deleteGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_group);

        final TextView msg = (TextView) findViewById(R.id.deleteMsg);



        // get group name
        Intent intent = getIntent();
        final String selectedGroup = intent.getStringExtra("groupname");


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3003/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<Void> call = ri.deleteAGroup(selectedGroup);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                msg.setText(t.getMessage());
            }
        });
        msg.setText("The Group Deleted Successfully");
        Call<Void> call2 = ri.deleteGroupUser(selectedGroup);
        call2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) { }

            @Override
            public void onFailure(Call<Void> call, Throwable t) { } });


    }
}
