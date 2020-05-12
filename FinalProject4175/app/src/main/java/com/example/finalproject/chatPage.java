package com.example.finalproject;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class chatPage extends AppCompatActivity {

    GoogleSignInAccount acc;
    EditText type;
    Button send;
    TextView disp;
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        Intent intent = getIntent();
        final String selectedGroup = intent.getStringExtra("groupname");

        type = findViewById(R.id.type);
        send = findViewById(R.id.send);
        disp = findViewById(R.id.chat_page);


        acc = login.getvalue();

        DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().child("Group_name");
        ValueEventListener listen1 = database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    String msg = s.getKey();

                    if(msg==selectedGroup)
                    {
                        c=1;
                    }
                    else{
                        c=0;
                    }


                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(c==1)
        {
            FriendlyMessage msg = new FriendlyMessage(selectedGroup);

            FirebaseDatabase database1 = FirebaseDatabase.getInstance();

            FirebaseDatabase.getInstance().getReference().child("Group_name").child(selectedGroup)
                    .setValue(msg).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(chatPage.this, "chat saved", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(chatPage.this, "chat not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



        //to get messages
        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Group_name").child(selectedGroup);
        ValueEventListener listen = database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                disp.setText("");
                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    String msg = s.child("text").getValue(String.class);
                    String name = s.child("name").getValue(String.class);


                    disp.append("\n"+name + ":   " + msg);
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String t = String.valueOf(type.getText());

                type.clearFocus();
                FriendlyMessage message = new FriendlyMessage(t,acc.getDisplayName());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String key = database.getReference("message").push().getKey();

                FirebaseDatabase.getInstance().getReference().child("Group_name").child(selectedGroup).child(key)
                        .setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(chatPage.this, "chat saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(chatPage.this, "chat not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}



