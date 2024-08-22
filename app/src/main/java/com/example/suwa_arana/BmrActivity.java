package com.example.suwa_arana;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BmrActivity extends AppCompatActivity {

    EditText et_weight, et_height, et_age;
    TextView result;
    String calculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        et_weight = findViewById(R.id.et_weight);
        et_height= findViewById(R.id.et_height);
        et_age = findViewById(R.id.et_age);
        result = findViewById(R.id.result);

    }

    public void calculate(View view) {


        String S1 = et_weight.getText().toString();
        String S2 = et_height.getText().toString();
        String S3 = et_age.getText().toString();

        //retrieve values
        float ageValue = Float.parseFloat(S3);
        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;

        //formula for calculate BMI
        float bmr = (float) (88.362 + (13.397 * weightValue) + (4.799 * heightValue) - (5.677 * ageValue));


        calculation = "Result:\n" + bmr + "\n";

        result.setText(calculation);
    }
}