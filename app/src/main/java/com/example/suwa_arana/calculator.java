package com.example.suwa_arana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class calculator extends AppCompatActivity {

    private Button btnc1,btnc2,btnc3,btnc4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btnc1=findViewById(R.id.btn_c1);
        btnc2=findViewById(R.id.btn_c2);
        btnc3=findViewById(R.id.btn_c3);
        btnc4=findViewById(R.id.btn_c4);

        btnc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(calculator.this,BmrActivity.class));
            }
        });

        btnc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(calculator.this, IWcalcActivity.class));
            }
        });
      btnc3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(calculator.this, bodyfat.class));
          }
      });
      btnc2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(calculator.this,BMICalActivity.class));
          }
      });

    }
}