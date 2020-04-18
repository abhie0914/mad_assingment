package com.example.p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class p1Login extends AppCompatActivity {
    EditText emailid;
    EditText passwordTX;
    Button loginBtns;
    Button signupBtns;
    Button newpasswordBtns;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1_login);
        emailid = findViewById(R.id.emailTxt);
        passwordTX = findViewById(R.id.passwordTxt);
        loginBtns = findViewById(R.id.loginBtn);
        signupBtns = findViewById(R.id.singupBtn);
        newpasswordBtns = findViewById(R.id.newpasswordBtn);
        signupBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(p1Login.this,MainActivity.class));

            }
        });


        loginBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = emailid.getText().toString();
                String Password = passwordTX.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();


                if(Email.isEmpty()){
                    emailid.setError("Enter email ID");
                    emailid.requestFocus();
                }else if (Password.isEmpty()){
                    passwordTX.setError("Enter password");
                    passwordTX.requestFocus();
                }else if (!Email.isEmpty() && !Password.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                    startActivity(new Intent(p1Login.this,Home.class));

                                    Toast.makeText(p1Login.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                                finishAffinity();
                                }else {
                                    Toast.makeText(p1Login.this, "Verify email", Toast.LENGTH_SHORT).show();
                                }
                               }else {
                                Toast.makeText(p1Login.this, "Check user login details", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        newpasswordBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(p1Login.this,newPassword.class));

            }
        });
    }
}
