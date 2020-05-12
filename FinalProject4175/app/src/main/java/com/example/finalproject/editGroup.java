package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editGroup extends AppCompatActivity {

    String email;
    String event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        Intent intent = getIntent();
        final String selectedGroup = intent.getStringExtra("groupname");


        final EditText editGroupName = (EditText) findViewById(R.id.editGroupName);
        final EditText editGroupDesc = (EditText) findViewById(R.id.editGroupDesc);
        final EditText editGroupLocation = (EditText) findViewById(R.id.editGroupLocation);
        final EditText editGroupHash = (EditText) findViewById(R.id.editGroupInterest);

        final Button btnEditGroup = (Button) findViewById(R.id.btnEditGroup);


        // call API

       final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3003/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<Groups>> call = ri.getAgroup(selectedGroup);
        call.enqueue(new Callback<List<Groups>>() {
            @Override
            public void onResponse(Call<List<Groups>> call, Response<List<Groups>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(editGroup.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Groups> aGroup = response.body();

                for(Groups g : aGroup) {
                    editGroupName.setText(g.getGroupName());
                    editGroupDesc.setText(g.getGroupDesc());
                    editGroupLocation.setText(g.getGroupLocation());
                    editGroupHash.setText(g.getInterest());
                    event = g.getEventName();
                    email = g.getUserEmail();


                }
            }

            @Override
            public void onFailure(Call<List<Groups>> call, Throwable t) {

            }
        });

        btnEditGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Groups groups = new Groups(editGroupName.getText().toString(), editGroupDesc.getText().toString(), editGroupLocation.getText().toString(),
                        editGroupHash.getText().toString(), event, email);
                Call<Groups> call = ri.putGroup(selectedGroup,groups);
                call.enqueue(new Callback<Groups>() {
                    @Override
                    public void onResponse(Call<Groups> call, Response<Groups> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(editGroup.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                        Groups groupsResponse = response.body();


                    }

                    @Override
                    public void onFailure(Call<Groups> call, Throwable t) {

                    }
                });

                Call<List<Group_User>> call3 = ri.getGroup_User();
                call3.enqueue(new Callback<List<Group_User>>() {
                    @Override
                    public void onResponse(Call<List<Group_User>> call, Response<List<Group_User>> response) {
                        final List<Group_User> gu2 = response.body();
                        for(Group_User gu3 : gu2) {
                            if(gu3.GroupName.equals(selectedGroup)) {
                               Group_User groupuser = new Group_User(gu3.UserEmail, editGroupName.getText().toString(),
                                        gu3.GroupUserId);
                                Call<Group_User> call4 = ri.putGroupUser(gu3.GroupUserId, groupuser);
                                call4.enqueue(new Callback<Group_User>() {
                                    @Override
                                    public void onResponse(Call<Group_User> call, Response<Group_User> response) {  }
                                    @Override
                                    public void onFailure(Call<Group_User> call, Throwable t) {  }});
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Group_User>> call, Throwable t) {

                    }
                });
                Toast.makeText(editGroup.this, "Updated!", Toast.LENGTH_SHORT).show();
                //Intent intent3 = new Intent(editGroup.this, accountActivity.class);
                //startActivity(intent3);
            }
        });



    }
}
