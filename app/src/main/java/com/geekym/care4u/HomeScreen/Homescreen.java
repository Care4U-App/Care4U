package com.geekym.care4u.HomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.geekym.care4u.HomeScreen.CovidCases.WorldDataActivity;
import com.geekym.care4u.HomeScreen.FoodForYou.Food_For_You;
import com.geekym.care4u.R;
import com.geekym.care4u.User_Details;
import com.geekym.care4u.Users;
import com.geekym.care4u.HomeScreen.VaccineSlot.Vaccine_Slot;
import com.geekym.care4u.authentication.Vaccine_Details;
import com.geekym.care4u.authentication.Login_Page;
import com.geekym.care4u.HomeScreen.selfAssessment.Self_Assessment;
import com.geekym.care4u.HomeScreen.vaccineCertificateValidation.ScanQR;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homescreen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    //Declaring Variables
    Button lout, QR, Food;
    CardView selftest, tracker, vslot, safety;
    TextView welcome;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

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
        Food = findViewById(R.id.food);
        QR = findViewById(R.id.QRCode);

        //Firebase get username
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);

                if (userprofile != null){
                    String fullname = userprofile.name;

                    welcome.setText("Welcome" + " " + fullname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                welcome.setText("Welcome");
            }
        });
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
                        FirebaseAuth.getInstance().signOut();
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
        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(Homescreen.this, Food_For_You.class);
                startActivity(g);
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
                SharedPreferences preferences = getSharedPreferences("ud", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("update_ud", "true");
                editor.apply();
                Intent b = new Intent(Homescreen.this, User_Details.class);
                startActivity(b);
                finishAffinity();
                break;

            case R.id.Update_vaccine:
                Intent c = new Intent(Homescreen.this, Vaccine_Details.class);
                startActivity(c);
                finishAffinity();
                break;

            case R.id.Reset:
                Intent d = new Intent(Homescreen.this, User_Details.class);
                startActivity(d);
                finishAffinity();
                break;

            case R.id.help:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode("+911123978046")));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
                break;

            default:
                return false;
        }
        return false;
    }
}