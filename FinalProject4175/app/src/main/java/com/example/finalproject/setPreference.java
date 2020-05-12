package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class setPreference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._setpreference);

        String[] language = {"English", "Spanish", "Korean", "Mandarin", "Russian","Turkish"};
        String[] food = {"a", "b", "c", "d", "e", "f","g"};

        LinearLayout languagell = (LinearLayout) findViewById(R.id.languagell);
        LinearLayout foodll = (LinearLayout) findViewById(R.id.foodll);
        LinearLayout interestll = (LinearLayout) findViewById(R.id.interestll);
        LinearLayout etcll = (LinearLayout) findViewById(R.id.etcll);

        for(int i = 0; i < language.length; i++) {
            CheckBox ckb = new CheckBox(this);
            ckb.setText(language[i]);
            ckb.setId(i);
            languagell.addView(ckb);
        }
        languagell.setScrollContainer(true);
        for(int i = 0; i < food.length; i++) {
            CheckBox ckb = new CheckBox(this);
            ckb.setText(food[i]);
            ckb.setId(i);
            foodll.addView(ckb);
        }

        final Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(setPreference.this, MainPage.class);
                startActivity(k);
            }
        });
    }
}
