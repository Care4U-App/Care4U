package com.geekym.care4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Login_Page extends AppCompatActivity {
    RadioGroup toggle;
    RadioButton signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);

        addfragment(new LoginFragment());

        toggle = findViewById(R.id.toggle);
        signup = findViewById(R.id.signupbutton);
        login = findViewById(R.id.loginbutton);

        toggle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.signupbutton){
                    replacefragment(new SignUpFragment());
                }
                else if (checkedId == R.id.loginbutton){
                    addfragment(new LoginFragment());
                }
            }
        });
    }
    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    private void addfragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }
}