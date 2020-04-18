package com.example.p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class newPassword extends AppCompatActivity {
    EditText email;
    Button pass;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        email = findViewById(R.id.emailTxtt);
        pass = findViewById(R.id.btnnewpassword);
        firebaseAuth = FirebaseAuth.getInstance();
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(newPassword.this, "Check email", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(newPassword.this, "Try again", Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });

    }
}
