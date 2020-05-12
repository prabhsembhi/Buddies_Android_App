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


public class eventActivity extends AppCompatActivity {

    int logo = R.drawable.event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // fab

        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
                Intent intent = new Intent(eventActivity.this, createEvent.class);
                startActivity(intent);
            }
        });

        // botton naviagtion

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_events:
                        break;
                    case R.id.navigation_home:
                        Intent intent2 = new Intent(eventActivity.this, MainPage.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_account:
                        Intent intent3 = new Intent(eventActivity.this, accountActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_group:
                        Intent intent4 = new Intent(eventActivity.this, groupActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });

        // recycler view

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3003/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<Event>> call = ri.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

               final List<Event> eventsList = response.body();

               final CustomAdapter customAdapter = new CustomAdapter(eventsList,logo);
               final SearchView searchView = (SearchView) findViewById(R.id.searchView);

                recyclerView.setAdapter(customAdapter);
                GridLayoutManager layoutManager = new GridLayoutManager(eventActivity.this,1);
                recyclerView.setLayoutManager(layoutManager);


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        s = s.toLowerCase();
                        List<Event> myList = new ArrayList<>();
                        myList.clear();
                        for(Event e : eventsList) {
                            String e_name = e.getEventName().toLowerCase();
                            String e_location = e.getEventLocation().toLowerCase();
                            String e_group = e.getGroupName();

                                if (e_name.contains(s)) {
                                    myList.add(e);
                                }
                                else if (e_location.contains(s)) {
                                    myList.add(e);
                                }
                                else if (e_group.contains(s)) {
                                    myList.add(e);
                                }

                        }

                        customAdapter.changeData(myList, logo);

                        if(s.length()==0) {
                            customAdapter.changeData(eventsList, logo);

                        }


                        return false;
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

    }


}
