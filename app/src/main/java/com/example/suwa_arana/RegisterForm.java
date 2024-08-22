package com.example.suwa_arana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class RegisterForm extends AppCompatActivity {

    private EditText et_name, et_license, et_email, et_gender, et_pnumber, et_speciality, et_language;
    private Button button, button2;
    private FirebaseFirestore db;
    private String uId, uName, uLicense, uEmail, uGender, uPnumber, uSpeciality, uLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);


        et_name = findViewById(R.id.et_name);
        et_license = findViewById(R.id.et_license);
        et_email = findViewById(R.id.et_email);
        et_gender = findViewById(R.id.et_gender);
        et_pnumber = findViewById(R.id.et_pnumber);
        et_speciality = findViewById(R.id.et_speciality);
        et_language = findViewById(R.id.et_language);
        button = findViewById(R.id.btn_privacy);
        button2 = findViewById(R.id.btn_h2);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            button.setText("Update");
            uId = bundle.getString("uId");
            uName = bundle.getString("uName");
            uLicense = bundle.getString("uLicense");
            uEmail = bundle.getString("uEmail");
            uGender = bundle.getString("uGender");
            uPnumber = bundle.getString("uPnumber");
            uSpeciality = bundle.getString("uSpeciality");
            uLanguage = bundle.getString("uLanguage");
            et_name.setText(uName);
            et_license.setText(uLicense);
            et_email.setText(uEmail);
            et_gender.setText(uGender);
            et_pnumber.setText(uPnumber);
            et_speciality.setText(uSpeciality);
            et_language.setText(uLanguage);

        }else{
            button.setText("Submit");

        }


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterForm.this,showDoctor.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String license = et_license.getText().toString();
                String email = et_email.getText().toString();
                String gender = et_gender.getText().toString();
                String pnumber = et_pnumber.getText().toString();
                String speciality = et_speciality.getText().toString();
                String language = et_language.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null){
                    String id = uId;
                    updateToFireStore(id,name,license,email,gender,pnumber,speciality,language);

                }else{
                    String id = UUID.randomUUID().toString();
                    saveToFirestore(id, name, license, email, gender, pnumber, speciality, language);
                }


            }
        });

    }
    private void updateToFireStore(String id, String name, String license, String email, String gender, String pnumber, String speciality, String language){
        db.collection("DoctorR").document(id).update("name", name, "license", license, "email", email, "gender", gender, "pnumber", pnumber, "speciality", speciality, "language", language)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterForm.this, "Data Updated", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisterForm.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterForm.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFirestore(String id, String name, String license, String email, String gender, String pnumber, String speciality, String language) {
        if (!name.isEmpty() && !email.isEmpty() && !gender.isEmpty() && !pnumber.isEmpty() && !speciality.isEmpty() && !language.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("license", license);
            map.put("email", email);
            map.put("gender", gender);
            map.put("pnumber", pnumber);
            map.put("speciality", speciality);
            map.put("language", language);

            db.collection("DoctorR").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterForm.this, "Data saved!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterForm.this, "Failed!", Toast.LENGTH_SHORT).show();

                }
            });


        } else
            Toast.makeText(this, "Empty fields not Allowed", Toast.LENGTH_SHORT).show();

    }
}