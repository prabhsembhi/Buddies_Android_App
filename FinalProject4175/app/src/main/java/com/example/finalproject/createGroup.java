package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createGroup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        final Button btnCreate = (Button) findViewById(R.id.btnCreateGroup);

        final EditText et1_g = (EditText) findViewById(R.id.et1_g);
        final EditText et3_g = (EditText) findViewById(R.id.et3_g);
        final EditText et4_g = (EditText) findViewById(R.id.et4_g);
        final EditText et5_g = (EditText) findViewById(R.id.et5_g);
        GoogleSignInAccount GOOGLE = login.getvalue();
        final String email = GOOGLE.getEmail();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3003/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface ri = retrofit.create(RetrofitInterface.class);

                Groups groups = new Groups(et1_g.getText().toString(), et3_g.getText().toString(), et4_g.getText().toString(),et5_g.getText().toString(), null, email);
                Call<Groups> call = ri.createPost(groups);
                call.enqueue(new Callback<Groups>() {
                    @Override
                    public void onResponse(Call<Groups> call, Response<Groups> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(createGroup.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                        Groups groupsResponse = response.body();


                    }

                    @Override
                    public void onFailure(Call<Groups> call, Throwable t) {

                    }
                });
                Group_User gu = new Group_User(email,et1_g.getText().toString());
                Call<Group_User> call2 = ri.createPost(gu);
                call2.enqueue(new Callback<Group_User>() {
                    @Override
                    public void onResponse(Call<Group_User> call, Response<Group_User> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(createGroup.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                        Group_User groupUserResponse = response.body();
                    }

                    @Override
                    public void onFailure(Call<Group_User> call, Throwable t) {

                    }
                });

                Intent intent2 = new Intent(createGroup.this, groupActivity.class);
                startActivity(intent2);
            }
        });
    }


}
