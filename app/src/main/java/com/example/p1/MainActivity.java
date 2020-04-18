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

public class MainActivity extends AppCompatActivity {
    EditText emailID;
    EditText PasswordT;
    Button signUpBtn;
    Button LoginBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailID = findViewById(R.id.emailText);
        PasswordT = findViewById(R.id.passwordText);
        signUpBtn = findViewById(R.id.singupBtn);
        LoginBtn = findViewById(R.id.loginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,p1Login.class));


            }
        });



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String password = PasswordT.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if (email.isEmpty()){
                    emailID.setError("Enter your email ID");
                    emailID.requestFocus();
                }else if (password.isEmpty()){
                    PasswordT.setError("Enter your password");
                    PasswordT.requestFocus();
                }else if (!email.isEmpty() && !password.isEmpty()){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Account exist", Toast.LENGTH_SHORT).show();
                            }else {
                                firebaseAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MainActivity.this, "Singup Successfull Check your email to verify your email", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });


                            }

                        }
                    });
                }
            }
        });


    }
}
