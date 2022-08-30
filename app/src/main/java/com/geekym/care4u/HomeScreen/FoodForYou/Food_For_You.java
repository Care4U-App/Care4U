package com.geekym.care4u.HomeScreen.FoodForYou;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;

public class Food_For_You extends AppCompatActivity {

    private Button b2home;
    CardView Veg, fruit, meat, cereal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_food_for_you);

            b2home = (Button) findViewById(R.id.f2home);
            Veg = (CardView) findViewById(R.id.veg);
            fruit = findViewById(R.id.fruits);
            meat = findViewById(R.id.protein);
            cereal = findViewById(R.id.cereal);

            b2home.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(Food_For_You.this, Homescreen.class);
                    startActivity(intent);
                    finishAffinity();
                }
            });

            Veg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Food_For_You.this, Vegetables.class);
                    startActivity(intent);
                    finish();
                }
            });

            fruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(Food_For_You.this, Fruits.class);
                    startActivity(intent);
                    finish();
                }
            });

            meat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(Food_For_You.this, Protiens.class);
                    startActivity(intent);
                    finish();
                }
            });

            cereal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(Food_For_You.this, Cereals.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }
