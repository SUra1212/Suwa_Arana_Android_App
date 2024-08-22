package com.example.suwa_arana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bodyfat extends AppCompatActivity {

    EditText age,neck,waist,hip;
    TextView tv_calculate;
    String calculation;
    Button btn_calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodyfat);

        age = findViewById(R.id.et_age);
        neck = findViewById(R.id.et_neck);
        waist = findViewById(R.id.et_waist);
        hip = findViewById(R.id.et_hip);
        tv_calculate = findViewById(R.id.tv_calculate);
        btn_calculate=findViewById(R.id.btn_calculate);
    }

    public void CalculateBodyFat(View view) {
        String st1 = age.getText().toString();
        String st4 = neck.getText().toString();
        String st5 = waist.getText().toString();
        String st6 = hip.getText().toString();

        float ageValue = Float.parseFloat(st1);
        float neckValue = Float.parseFloat(st4);
        float waistValue = Float.parseFloat(st5);
        float hipValue = Float.parseFloat(st6);

        //circumference value of body fat
        float bodyFat = (waistValue+hipValue)-neckValue;

        calculation = "Result:" +bodyFat;

        tv_calculate.setText(calculation);
    }
}