package com.example.suwa_arana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        Button btn_sinhala = findViewById(R.id.btn_sinhala);
        Button btn_english = findViewById(R.id.btn_english);
        Button btn_tamil = findViewById(R.id.btn_tamil);

        btn_sinhala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectLanguageActivity.this, com.example.suwa_arana.PrivacyPolicyActivity.class));
            }
        });

        btn_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectLanguageActivity.this, com.example.suwa_arana.PrivacyPolicyActivity.class));
            }
        });
        btn_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectLanguageActivity.this, com.example.suwa_arana.PrivacyPolicyActivity.class));
            }
        });
    }
}