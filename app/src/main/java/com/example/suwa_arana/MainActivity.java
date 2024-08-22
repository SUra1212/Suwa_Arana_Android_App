package com.example.suwa_arana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button signOut;
    private FirebaseAuth mAuth;
    private Button btnh1,btnh2,btnh3,btnh4;
    private Button btnh5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signOut=findViewById(R.id.btn_signOut);
        mAuth=FirebaseAuth.getInstance();
        btnh1=findViewById(R.id.btn_h1);
        btnh2=findViewById(R.id.btn_h2);
        btnh3=findViewById(R.id.btn_h3);
        btnh4=findViewById(R.id.btn_h4);
        btnh5=findViewById(R.id.buttonnew5);


        btnh5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,calculator.class));
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,SingIn.class));

            }
        });
        btnh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterForm.class));
            }
        });
        btnh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });
        btnh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ChannelActivity.class));
            }
        });
        btnh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

    }
}