package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        GoogleSignInAccount GOOGLE = login.getvalue();
        final String name = GOOGLE.getDisplayName();
        final String email = GOOGLE.getEmail();

        final TextView tv1 = (TextView) findViewById(R.id.etProfile1);
        final TextView tv2 = (TextView) findViewById(R.id.etProfile2);

        final EditText et1 = (EditText) findViewById(R.id.et_about);
        final EditText et2 = (EditText) findViewById(R.id.et_hash);
        final Button btn = (Button) findViewById(R.id.btnProfileBack);

        tv1.setText(name);
        tv2.setText(email);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3003/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface ri = retrofit.create(RetrofitInterface.class);

                User user= new User(email, name, et2.getText().toString(),et1.getText().toString());
                Call<User> call2 = ri.putUser(email,user);
                call2.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) { }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) { } });


                Intent intent = new Intent(editProfile.this, accountActivity.class);
                startActivity(intent);
            }
        });
    }
}
