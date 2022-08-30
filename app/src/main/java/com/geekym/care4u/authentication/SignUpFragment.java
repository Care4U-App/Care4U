package com.geekym.care4u.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.geekym.care4u.R;
import com.geekym.care4u.Users;
import com.geekym.care4u.authentication.Login_Page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {

    private FirebaseAuth mAuth;

    View view;
    EditText email, pass, confpass,name;
    Button signup;
    String emailtext,passtext,confpasstext;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        email = getView().findViewById(R.id.emailsignup);
        pass = getView().findViewById(R.id.passsignup);
        confpass = getView().findViewById(R.id.confpasssignup);
        signup = getView().findViewById(R.id.buttonsignup);
        name = getView().findViewById(R.id.name);
        ProgressBar progressBar = getView().findViewById(R.id.progressBar);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametext = name.getText().toString().trim();
                emailtext = email.getText().toString().trim();
                passtext = pass.getText().toString().trim();
                confpasstext = confpass.getText().toString().trim();

                if (nametext.isEmpty()){
                    name.setError("Field can't be empty");
                    name.requestFocus();
                    return;
                }

                if (emailtext.isEmpty()){
                    email.setError("Field can't be empty");
                    email.requestFocus();
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
                    email.setError("Please enter a valid email address");
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
                else if (confpasstext.isEmpty()){
                    confpass.setError("Field can't be empty");
                    confpass.requestFocus();
                    return;
                }
                else if (!confpasstext.equals(passtext)){
                    confpass.setError("Password did not match");
                    confpass.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(emailtext,passtext)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Users users = new Users(nametext,emailtext);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                Intent intent = new Intent(getActivity(), Login_Page.class);
                                                startActivity(intent);
                                                Animatoo.animateFade(getContext());
                                                getActivity().finishAffinity();
                                            }
                                            else{
                                                Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }
}