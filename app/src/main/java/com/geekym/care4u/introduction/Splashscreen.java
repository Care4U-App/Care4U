package com.geekym.care4u.introduction;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.geekym.care4u.R;
import com.geekym.care4u.authentication.Login_Page;

public class Splashscreen extends AppCompatActivity {
    //Here we save onBoardingScreen status
    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);

        //Here we check connectivity of the device
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Here we check network info
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo==null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
            Toast.makeText(Splashscreen.this, "Not Connected to internet!", Toast.LENGTH_SHORT).show();
        }       //It drop a toast if there is no active internet connection detected

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);
                //First time it will show Onboarding Screen
                if (isFirstTime){
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent i=new Intent(Splashscreen.this, OBS.class);
                    startActivity(i);
                    finish();
                    //After first time it will take the user to Login_Page
                }else{
                    Intent i=new Intent(Splashscreen.this, Login_Page.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000); //Splashscreen stays for 3 seconds only
    }
}