package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class accountActivity extends AppCompatActivity {

    private TextView profileName, profileEmail;
    String user_name, user_email;
    private ImageView profileImage;
    GoogleSignInClient googleSignInClient;
    Button sign_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        // navigation
        profileImage = findViewById(R.id.imageView3);
        sign_out = findViewById(R.id.sign_out);
          //google sign in procedures

        setDataOnView();
        GoogleSignInAccount GOOGLE = login.getvalue();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
            // google sign in procedure ends

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView4);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_events:
                        Intent intent = new Intent(accountActivity.this, eventActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_home:
                        Intent intent2 = new Intent(accountActivity.this, MainPage.class);
                        startActivity(intent2);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_account:
                        break;
                    case R.id.navigation_group:
                        Intent intent4 = new Intent(accountActivity.this, groupActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(0, 0);
                        break;
                }
                return true;
            }
        });

        // display user

        final TextView fn = (TextView) findViewById(R.id.FN);
        final TextView email = (TextView) findViewById(R.id.emailofuser);
        final TextView about = (TextView) findViewById(R.id.about);
        final TextView hash = (TextView) findViewById(R.id.hash);

        final Button btnEdit = (Button) findViewById(R.id.btnEdit);
        final Button btnShow = (Button) findViewById(R.id.btnShowGroup);
        final Button btnMangage = (Button) findViewById(R.id.btnMangeGroup);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3003/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        Call<List<User>> call = ri.getAuser(user_email);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                final List<User> userList = response.body();

                if(response.body().isEmpty()) {
                    // create user
                    // public User(String UserEmail,String FullName,String Preference, String About)
                    User user= new User(user_email, user_name, null,null);
                    Call<User> call2 = ri.createUser(user);
                    call2.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) { }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) { } });

                    Call<List<User>> call3 = ri.getAuser(user_email);
                    call3.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {

                        }
                    });
                    Intent intent = new Intent(accountActivity.this, accountActivity.class);
                    startActivity(intent);

                }
                else {
                    for(User u : userList) {
                        email.setText(u.getUserEmail());
                        fn.setText(u.getFullName());
                        about.setText(u.getAbout());
                        hash.setText(u.getPreference());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


        // edit profile

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(accountActivity.this, editProfile.class);
                startActivity(intent);
            }
        });

        // show groups

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent4 = new Intent(accountActivity.this, showUserGroup.class);
                intent4.putExtra("email", email.getText().toString());
                startActivity(intent4);

            }
        });

        // manage groups
        btnMangage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(accountActivity.this, manageGroup.class);
                intent4.putExtra("email", email.getText().toString());
                startActivity(intent4);
            }
        });


        //google sign out button
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */

                googleSignInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent = new Intent(accountActivity.this, login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void setDataOnView() {
        GoogleSignInAccount GOOGLE_ACCOUNT = login.getvalue();
        Picasso.get().load(GOOGLE_ACCOUNT.getPhotoUrl()).centerInside().fit().into(profileImage);
       user_name =(GOOGLE_ACCOUNT.getDisplayName());
       user_email = (GOOGLE_ACCOUNT.getEmail());
    }
}
