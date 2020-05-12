package com.example.finalproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class createEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        final Button btnEvent = (Button) findViewById(R.id.btnCreateEvent);

        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        final EditText et3 = (EditText) findViewById(R.id.et3);
        final EditText et4 = (EditText) findViewById(R.id.et4);
        final EditText et5 = (EditText) findViewById(R.id.et5);
        final EditText et6 = (EditText) findViewById(R.id.et6);



        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3003/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface ri = retrofit.create(RetrofitInterface.class);

                Event event = new Event(et2.getText().toString(), et1.getText().toString(), et4.getText().toString(),et3.getText().toString(),
                        et5.getText().toString(), et6.getText().toString());
                Call<Event> call = ri.createPost(event);
                call.enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(createEvent.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                        Event eventResponse = response.body();


                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {

                    }
                });

                Intent intent2 = new Intent(createEvent.this, eventActivity.class);
                startActivity(intent2);

            }
        });
    }


}
