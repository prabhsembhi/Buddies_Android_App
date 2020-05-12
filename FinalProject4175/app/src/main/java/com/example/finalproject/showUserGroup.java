package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class showUserGroup extends AppCompatActivity {
    int logo = R.drawable.group;
    final String url = "http://10.0.2.2:3003/";
    List<Groups> listOfGroup = new ArrayList<>();
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_group);


        // get intent
        Intent intent = getIntent();
        String useremail = intent.getStringExtra("email");

        final RecyclerView recyclerViewUser = (RecyclerView) findViewById(R.id.recyclerViewForUser);
        final Button btnBack2 = (Button) findViewById(R.id.backToAccount2);

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(showUserGroup.this, accountActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        i = 0;
        final RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<Group_User>> call = ri.getGroup_UserByEamil(useremail);
        call.enqueue(new Callback<List<Group_User>>() {
            @Override
            public void onResponse(Call<List<Group_User>> call, Response<List<Group_User>> response) {
                final List<Group_User> gu = response.body();

                for(Group_User g : gu) {
                    //userGroupList.add(g.GroupName);
                    Call<List<Groups>> call2 = ri.getAgroup(g.GroupName);
                    call2.enqueue(new Callback<List<Groups>>() {

                        @Override
                        public void onResponse(Call<List<Groups>> call2, Response<List<Groups>> response) {
                            if(!response.isSuccessful()) {

                                return;
                            }
                            final List<Groups> temp= response.body();

                            for(Groups g2 : temp) {
                                Groups temp2 = new Groups(g2.getGroupName(), g2.getGroupDesc(), g2.GroupLocation, g2.getInterest(),
                                        g2.getEventName(), g2.getUserEmail());
                                listOfGroup.add(temp2);
                            }

                            i++;

                            CustomAdapter2 customAdapter2 = new CustomAdapter2(listOfGroup,logo);

                            recyclerViewUser.setAdapter(customAdapter2);
                            GridLayoutManager layoutManager = new GridLayoutManager(showUserGroup.this,1);
                            recyclerViewUser.setLayoutManager(layoutManager);



                        }


                        @Override
                        public void onFailure(Call<List<Groups>> call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<Group_User>> call, Throwable t) {

            }
        });


        /*
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
*/



    }
}
