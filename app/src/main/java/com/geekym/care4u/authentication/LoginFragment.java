package com.geekym.care4u.authentication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.geekym.care4u.R;
import com.geekym.care4u.User_Details;
import com.geekym.care4u.authentication.ForgetPass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private FirebaseAuth Auth;
    private ProgressBar progressBar;

    View view;
    EditText email,pass;
    Button login;
    TextView frgtpass;
    CheckBox checkBox;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Auth = FirebaseAuth.getInstance();
        email = getView().findViewById(R.id.loginemail);
        pass = getView().findViewById(R.id.loginpass);
        login = getView().findViewById(R.id.btnlogin);
        frgtpass = getView().findViewById(R.id.forgetpass);
        progressBar = getView().findViewById(R.id.progressBar2);
        checkBox = getView().findViewById(R.id.checkBox);


        //Remember me checkbox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getActivity().getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                }
                else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getActivity().getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });

        frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForgetPass.class);
                startActivity(intent);
                Animatoo.animateShrink(getContext());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailtext = email.getText().toString().trim();
                String passtext = pass.getText().toString().trim();

                if (emailtext.isEmpty()){
                    email.setError("Field can't be empty");
                    email.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
                    email.setError("Please enter a valid Email id");
                    email.requestFocus();
                    return;
                }
                else if (passtext.isEmpty()){
                    pass.setError("Field can't be empty");
                    pass.requestFocus();
                    return;
                }
                else if (passtext.length()<6){
                    pass.setError("Password must be atleast 6 characters");
                    pass.requestFocus();
                    return;
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);

                    Auth.signInWithEmailAndPassword(emailtext,passtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user.isEmailVerified()){
                                    progressBar.setVisibility(View.GONE);
                                    Intent intent2 = new Intent(getActivity(), User_Details.class);
                                    startActivity(intent2);
                                    Animatoo.animateFade(getContext());
                                    getActivity().finishAffinity();
                                }
                                else{
                                    progressBar.setVisibility(View.GONE);
                                    user.sendEmailVerification();
                                    Toast.makeText(getActivity(), "Check your email to verify your account and Login again", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Failed to Login! Please check your credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }
}