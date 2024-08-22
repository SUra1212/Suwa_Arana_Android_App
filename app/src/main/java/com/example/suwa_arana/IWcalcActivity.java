package com.example.suwa_arana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IWcalcActivity extends AppCompatActivity {
Button ical;
EditText iht;
TextView irs1;

float iheight,iresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iwcalc);

        ical=findViewById(R.id.btnCalc);
        iht=findViewById(R.id.inputHeight);
        irs1=findViewById(R.id.textresult1);


        ical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iheight = Float.parseFloat(iht.getText().toString());

                iresult = (float) (50+(0.91*iheight-138.684));
                irs1.setText(String.valueOf(iresult+"kg"));
            }
        });
    }
}