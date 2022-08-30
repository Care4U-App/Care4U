package com.geekym.care4u.HomeScreen.FoodForYou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;

public class Protiens extends AppCompatActivity {

    ImageButton backfood, backhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_protiens);

            backfood = findViewById(R.id.backfood);
            backhome = findViewById(R.id.backhome);

            backfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Protiens.this, Food_For_You.class);
                    startActivity(intent);
                    finish();
                }
            });

            backhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Protiens.this, Homescreen.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        }

        @Override
        public void onBackPressed() {
            Intent intent = new Intent(Protiens.this, Food_For_You.class);
            startActivity(intent);
            super.onBackPressed();
        }
    }