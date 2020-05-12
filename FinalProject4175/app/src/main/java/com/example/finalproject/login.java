package com.example.finalproject;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class login extends AppCompatActivity {

    private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;//google sign in client
    private SignInButton googleSignInButton; //google sign in
    private final String login_pref = "login_pref";
    public static GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        googleSignInButton = findViewById(R.id.sign_in_button); //google sign in button

        //google sign in oblect to request basic information
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googleSignInClient = GoogleSignIn.getClient(this, gso);
        //launch the login screen using the Intent
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                Log.d("first", "yes ");
                startActivityForResult(signInIntent, 101);
            }
        });

        TextView admin = findViewById(R.id.adminSignin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, adminLogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            Log.d("pehla", "no ");
        switch (requestCode) {

            case 101:
                try {
                    // The Task returned from this call is always completed, no need to attach
                    // a listener.
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                     account = task.getResult(ApiException.class);
                    onLoggedIn();
                } catch (ApiException e) {
                    // The ApiException status code indicates the detailed failure reason.
                    Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                }
                break;
        }
    }

    private void onLoggedIn() {
        Intent intent = new Intent(this, accountActivity.class);
        startActivity(intent);
        finish();
    }

    public static GoogleSignInAccount getvalue()
    {
        GoogleSignInAccount googleSignInAccount = account;
        return googleSignInAccount;
    }


 /*  @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedIn();
        } else {
            Log.d(TAG, "Not logged in");
        }
    }*/
}
