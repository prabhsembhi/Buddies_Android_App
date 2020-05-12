package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class groupDetail extends AppCompatActivity {
    int logo = R.drawable.event;
    String groupname;
    int i;
    final String url = "http://10.0.2.2:3003/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);


        // get user email
        GoogleSignInAccount GOOGLE = login.getvalue();
        final String email = GOOGLE.getEmail();

        // get intent
        Intent intent = getIntent();

        String selectedGroup = intent.getStringExtra("SL");

        // instantiate textview

        final TextView tv1 = (TextView) findViewById(R.id.aGroupName);
        final TextView tv2 = (TextView) findViewById(R.id.aGroupDesc);
        final TextView tv3 = (TextView) findViewById(R.id.aGroupLocation);
        final TextView tv4 = (TextView) findViewById(R.id.aGroupHash);
        final TextView tv5 = (TextView) findViewById(R.id.aGroupManager);
        final Button btnJoin = (Button) findViewById(R.id.btnJoin);


        // call API

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<Groups>> call = ri.getAgroup(selectedGroup);
        call.enqueue(new Callback<List<Groups>>() {
            @Override
            public void onResponse(Call<List<Groups>> call, Response<List<Groups>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(groupDetail.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Groups> aGroup = response.body();

                for(Groups g : aGroup) {
                    groupname = g.getGroupName();
                    tv1.setText(g.getGroupName());
                    tv2.append(g.getGroupDesc());
                    tv3.append(g.getGroupLocation());
                    tv4.append(g.getInterest());
                    tv5.append(g.getUserEmail());
                }
            }

            @Override
            public void onFailure(Call<List<Groups>> call, Throwable t) {

            }
        });

        // recycler view

        final RecyclerView recyclerView_event = (RecyclerView)findViewById(R.id.recyclerView_events);


        Call<List<Event>> call2 = ri.getEventByGroup(selectedGroup);
        call2.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

                if(!response.isSuccessful()) {
                    Toast.makeText(groupDetail.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Event> eventsOfGroup = response.body();

                final CustomAdapter customAdapter = new CustomAdapter(eventsOfGroup,logo);

                recyclerView_event.setAdapter(customAdapter);
                GridLayoutManager layoutManager = new GridLayoutManager(groupDetail.this,1);
                recyclerView_event.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

        // join the group
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Group_User>> call3 = ri.getGroup_User();
                call3.enqueue(new Callback<List<Group_User>>() {
                    @Override
                    public void onResponse(Call<List<Group_User>> call, Response<List<Group_User>> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(groupDetail.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                        }
                        List<Group_User> groupuser = response.body();
                        // Toast.makeText(groupDetail.this, ""+groupname, Toast.LENGTH_SHORT).show();
                        i = 0;
                        for(Group_User gu : groupuser) {
                            if(gu.getGroupName().equals(groupname) && gu.getUserEmail().equals(email)) {

                                Toast.makeText(groupDetail.this, "You are already in thus group", Toast.LENGTH_SHORT).show();
                                i++;
                            }
                        }
                        if(i==0){
                            Group_User gu2 = new Group_User(email, groupname);
                            Call<Group_User> call4 = ri.createPost(gu2);
                            Toast.makeText(groupDetail.this, "You've now joined group", Toast.LENGTH_SHORT).show();
                            call4.enqueue(new Callback<Group_User>() {
                                @Override
                                public void onResponse(Call<Group_User> call, Response<Group_User> response) { }
                                @Override
                                public void onFailure(Call<Group_User> call, Throwable t) { } });
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Group_User>> call, Throwable t) { } });



            }
        });

    }
}
