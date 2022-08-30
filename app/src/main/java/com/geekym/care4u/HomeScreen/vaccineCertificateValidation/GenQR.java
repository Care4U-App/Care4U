package com.geekym.care4u.HomeScreen.vaccineCertificateValidation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.geekym.care4u.HomeScreen.Homescreen;
import com.geekym.care4u.R;
import com.google.zxing.WriterException;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenQR extends AppCompatActivity {

    Button back, scan;
    ImageView imageView;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String fin="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gen_qr);


        SharedPreferences st = getApplicationContext().getSharedPreferences("vacc_details", Context.MODE_PRIVATE);
        fin=st.getString("status", "");
        imageView = findViewById(R.id.iv_out);
        back = findViewById(R.id.button);
        scan = findViewById(R.id.scan);

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(fin, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenQR.this, Homescreen.class);
                startActivity(intent);
                finish();
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(GenQR.this, ScanQR.class);
                startActivity(x);
                finish();
            }
        });
    }

    private boolean checkPermission(String permission) {
        int result= ContextCompat.checkSelfPermission(GenQR.this,permission);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }
    private void requestPermission(String permission1,int code){
        if(ActivityCompat.shouldShowRequestPermissionRationale(GenQR.this,permission1)){

        }
        else{
            ActivityCompat.requestPermissions(GenQR.this,new String[]{permission1},code);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent x = new Intent(GenQR.this, Homescreen.class);
        startActivity(x);
        finishAffinity();
    }
}