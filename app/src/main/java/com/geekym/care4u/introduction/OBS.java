package com.geekym.care4u.introduction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekym.care4u.R;
import com.geekym.care4u.authentication.Login_Page;

public class OBS extends AppCompatActivity {

    //Declaring Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button getstarted,nextbtn;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_obs);

        //Initializing variables with IDs
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getstarted = findViewById(R.id.get_started_btn);
        nextbtn = findViewById(R.id.next_btn);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0); //Slider Position
        viewPager.addOnPageChangeListener(changeListener);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OBS.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void skip(View view){  //When skipped
        startActivity(new Intent(this, Login_Page.class));
        finish();
    }

    public void next(View view){ //Next Slide
        viewPager.setCurrentItem(currentPosition + 1);
    }

    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;
            if (position == 0){
                getstarted.setVisibility(View.INVISIBLE);
            }else if (position == 1){
                getstarted.setVisibility(View.INVISIBLE);
            }else {
                animation = AnimationUtils.loadAnimation(OBS.this,R.anim.bottom_anim);
                getstarted.setAnimation(animation);
                nextbtn.setVisibility(View.INVISIBLE);
                getstarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}