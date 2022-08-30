package com.geekym.care4u.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;
import com.geekym.care4u.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Vaccine_Details extends AppCompatActivity {

    //Declaring Variables
    private final int CHOOSE_PDF_FROM_DEVICE = 1001;
    private static final String TAG = "Vaccine_Details";
    private Button choose, home;
    TextView path;
    Integer Flag = 0;
    String status, namecheck;
    SharedPreferences st;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID, bfid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_vaccine_details);

        //Initializing variables with IDs
        choose = findViewById(R.id.upload);
        path = findViewById(R.id.path);
        home = findViewById(R.id.conf);
        st = getSharedPreferences("vacc_details", Context.MODE_PRIVATE);
        //Firebase get username
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        String bid = sp.getString("saved_bid", "");         //BID details
        bfid = bid;

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);
                if (userprofile != null){
                    namecheck = userprofile.name;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                namecheck = "";
            }
        });

        //To select PDF (COVID-19 Vaccination Certificate)
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callChooseFileFromDevice();
            }
        });

        //Takes user to home (Proceed/Next Button)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vaccine_Details.this, Homescreen.class);
                if(Flag==0){                //This means no PDF is selected
                    Toast.makeText(Vaccine_Details.this, "User and Certificate Not Verified!", Toast.LENGTH_SHORT).show();
                    //status = "None";
                    status = "tus5s82@fjt";
                    SharedPreferences.Editor editor = st.edit();
                    editor.putString("status", status);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }else  if(Flag==1) {        //This means 1st Dose Vaccination Certificate if selected
                    Toast.makeText(Vaccine_Details.this, "Verified! Partially Vaccinated", Toast.LENGTH_SHORT).show();
                    //status = "Half";
                    status = "cwrn29328nvfhr";
                    SharedPreferences.Editor editor = st.edit();
                    editor.putString("status", status);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }else if(Flag==2){          //This means 2nd Dose Vaccination Certificate if selected
                    Toast.makeText(Vaccine_Details.this, "Verified! Fully Vaccinated", Toast.LENGTH_SHORT).show();
                    //status = "Full";
                    status = "we0rmi3ir29njd";
                    SharedPreferences.Editor editor = st.edit();
                    editor.putString("status", status);
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void callChooseFileFromDevice(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent, CHOOSE_PDF_FROM_DEVICE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData){
        super.onActivityResult(requestCode, resultCode, resultData);
        if(requestCode == CHOOSE_PDF_FROM_DEVICE && resultCode == RESULT_OK);
        //The result data contains a URI for the path for the document directory that the user selected
        if(resultData!=null){
            Log.d(TAG, "onActivityResult: "+resultData.getData());
            path.setText("File Path: "+resultData.getData());       //Displays the path of the PDF selected
            extractText(resultData.getData());
        }
    }
    InputStream inputStream;
    public void extractText(Uri uri){
        try{
            inputStream = Vaccine_Details.this.getContentResolver().openInputStream(uri);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(Vaccine_Details.this, "File not Found!", Toast.LENGTH_SHORT).show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String content="";
                StringBuilder builder = new StringBuilder();
                PdfReader reader = null;
                try{
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);{
                        reader = new PdfReader(inputStream);
                        content = PdfTextExtractor.getTextFromPage(reader, 1);
                        builder.append(content);
                        if(content.contains("Fully Vaccinated") && content.contains(namecheck) && content.contains(bfid)){
                            Flag=2;
                        }else if(content.contains("Partially Vaccinated") || content.contains("Provisional Certificate for COVID-19 Vaccination") && content.contains(namecheck) && content.contains(bfid)){
                            Flag=1;
                        }
                    }
                    reader.close();
                }catch (IOException e){
                    Log.d(TAG, "run: "+e.getMessage());
                    Toast.makeText(Vaccine_Details.this, "Wrong PDF", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }
}