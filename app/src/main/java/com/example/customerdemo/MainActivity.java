package com.example.customerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
        private TextView tvEmail,tvPass;
        private Button btnLogin;
        private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmail=findViewById(R.id.tvEmail);
        tvPass=findViewById(R.id.tvPass);
        btnLogin=findViewById(R.id.btnLogin);

        tvEmail.setText("catluynh99@gmail.com");
        tvPass.setText("catluynh");

        auth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=tvEmail.getText().toString();
                String pass=tvPass.getText().toString();
                auth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(MainActivity.this, ListViewActivity.class);
                            startActivity(intent1);
                        } else {
                            Toast.makeText(MainActivity.this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}