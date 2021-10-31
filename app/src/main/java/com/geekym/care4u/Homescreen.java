package com.geekym.care4u;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.geekym.care4u.CovidCases.WorldDataActivity;
import com.geekym.care4u.VaccineSlot.Vaccine_Slot;

public class Homescreen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    //Declaring Variables
    Button lout, QR, Help;
    CardView selftest, tracker, vslot, safety;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_homescreen);

        //
        vslot =  findViewById(R.id.vaccine_slot);
        lout =  findViewById(R.id.logout);
        tracker = findViewById(R.id.covidtracker);
        safety = findViewById(R.id.Safety);
        selftest = findViewById(R.id.selftest);
        welcome = findViewById(R.id.greeting_name);
        Help = findViewById(R.id.helper);
        QR = findViewById(R.id.QRCode);

        //Receiving Name from User Details Activity via SharedPreferences
       // SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
      //  String name=sp.getString("saved_name", "");
      //  welcome.setText("Welcome "+name);

        //Safety Tips
        safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, Safety_Tips.class);
                startActivity(intent);
            }
        });

        //Live Covid Cases
        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this, WorldDataActivity.class);
                startActivity(intent);
            }
        });

        //QR Code Vaccine Verification/Confirmation
        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, ScanQR.class);
                startActivity(intent);
            }
        });

        //Vaccine Slot Checker
        vslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this, Vaccine_Slot.class);
                startActivity(intent);
            }
        });

        //Logout Button -> Login Page
        lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Homescreen.this);  //Creates a Pop-up Dialog
                alertDialogBuilder.setTitle("Confirm Logout?");
                alertDialogBuilder.setIcon(R.drawable.logo);
                alertDialogBuilder.setMessage("Do you really want to Logout?");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Homescreen.this, Login_Page.class);
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Homescreen.this, "Exit cancelled", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        //Covid India Helpline
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode("+911123978046")));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
            }
        });

        //Self Assessment
        selftest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this, Self_Assessment.class);
                startActivity(intent);
            }
        });
    }

    //When the user presses Navigation Back Button
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this); //Creates a Pop-up Dialog
        alertDialogBuilder.setTitle("Confirm Exit");
        alertDialogBuilder.setIcon(R.drawable.logo);
        alertDialogBuilder.setMessage("Do you really want to exit?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Homescreen.this, "Exit cancelled", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }
    // For opening the pop-up menu when clicked
    public  void showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }
    // For thw working of the buttons which are inside the pop-up menu
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.Update_user:
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Intent b = new Intent(Homescreen.this, User_Details.class);
                startActivity(b);
                finishAffinity();
                break;

            case R.id.Update_vaccine:
                SharedPreferences preferences1 = getSharedPreferences("save", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = preferences1.edit();
                editor1.putString("save1", "false");
                editor1.apply();
                Intent c = new Intent(Homescreen.this, Vaccine_Details.class);
                startActivity(c);
                finishAffinity();
                break;

            case R.id.Reset:
                SharedPreferences preferences2 = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences preferences3 = getSharedPreferences("save", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = preferences2.edit();
                SharedPreferences.Editor editor3 = preferences3.edit();
                editor2.putString("remember", "false");
                editor3.putString("save1", "false");
                preferences2.edit().remove("checkbox").commit();
                preferences3.edit().remove("save").commit();
                editor2.apply();
                editor3.apply();
                Intent d = new Intent(Homescreen.this, Login_Page.class);
                startActivity(d);
                finishAffinity();
                break;

            default:
                return false;
        }
        return false;
    }
}