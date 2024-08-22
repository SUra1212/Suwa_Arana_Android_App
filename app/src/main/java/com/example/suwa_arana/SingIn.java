package com.example.suwa_arana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingIn extends AppCompatActivity {

    private EditText mEmail, mPass;
    private TextView mTextView;
    private Button singInBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        mEmail = findViewById(R.id.email_regLogin);
        mPass = findViewById(R.id.email_pasLogin);
        singInBtn = findViewById(R.id.btn_h4);
        mTextView = findViewById(R.id.textView);

        mAuth = FirebaseAuth.getInstance();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingIn.this , login.class));
            }
        });
        singInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();

            }
        });
    }
    private void LoginUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!pass.isEmpty()){
              mAuth.signInWithEmailAndPassword(email,pass)
                      .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                          @Override
                          public void onSuccess(AuthResult authResult) {
                              Toast.makeText(SingIn.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(SingIn.this, MainActivity.class));
                              finish();
                          }
                      }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(SingIn.this, "Login Failed", Toast.LENGTH_SHORT).show();

                  }
              });

            }else{
                mPass.setError("Empty Fields Are Not Allowed");
            }
        }else if(email.isEmpty()){
            mEmail.setError("Empty Fields Are Not Allowed");
        }else{
            mEmail.setError("please Enter Correct Email");
        }
    }
}