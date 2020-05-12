package com.example.finalproject;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class groupActivity extends AppCompatActivity {
    int logo = R.drawable.group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        // fab

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
                Intent intent = new Intent(groupActivity.this, createGroup.class);
                startActivity(intent);
            }
        });


        // botton naviagtion

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView3);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_events:
                        Intent intent = new Intent(groupActivity.this, eventActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_home:
                        Intent intent2 = new Intent(groupActivity.this, MainPage.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_account:
                        Intent intent3 = new Intent(groupActivity.this, accountActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_group:

                        break;
                }
                return true;
            }
        });

        // recycler view

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        final SearchView searchView2 = (SearchView) findViewById(R.id.searchView2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3003/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<Groups>> call = ri.getGroups();
        call.enqueue(new Callback<List<Groups>>() {
            @Override
            public void onResponse(Call<List<Groups>> call, Response<List<Groups>> response) {
                if(!response.isSuccessful()) {

                    return;
                }

                final List<Groups> groupsList = response.body();

                final CustomAdapter2 customAdapter2 = new CustomAdapter2(groupsList,logo);
                recyclerView.setAdapter(customAdapter2);
                GridLayoutManager layoutManager = new GridLayoutManager(groupActivity.this,1);
                recyclerView.setLayoutManager(layoutManager);

                searchView2.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        s = s.toLowerCase();
                        List<Groups> myList2 = new ArrayList<>();
                        myList2.clear();
                        for(Groups g : groupsList) {
                            String g_name = g.getGroupName().toLowerCase();
                            String g_location = g.getGroupLocation().toLowerCase();
                            String g_event = g.getEventName();
                            String g_desc = g.getGroupDesc();

                            if (g_name.contains(s)) {
                                myList2.add(g);
                            }
                            else if (g_location.contains(s)) {
                                myList2.add(g);
                            }
                            else if (g_event.contains(s)) {
                                myList2.add(g);
                            }
                            else if(g_desc.contains(s)) {
                                myList2.add(g);
                            }

                        }

                        customAdapter2.changeData(myList2, logo);

                        if(s.length()==0) {
                            customAdapter2.changeData(groupsList, logo);

                        }
                        return false;
                    }
                });


            }

            @Override
            public void onFailure(Call<List<Groups>> call, Throwable t) {

            }
        });

    }
}
