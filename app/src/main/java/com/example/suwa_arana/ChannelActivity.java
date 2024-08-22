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

public class ChannelActivity extends AppCompatActivity {

    private EditText cDate , cName ,cGender , cAge , cPhone , cDisease , cDoctor;
    private Button cChannelBtn, cShowBtn;
    private FirebaseFirestore db;
    private String cuDate, cuName, cuGender, cuAge, cuPhone, cuDisease, cuDoctor, cuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

     cDate = findViewById(R.id.inputDate);
     cName = findViewById(R.id.inputClientname);
     cGender = findViewById(R.id.inputGender);
     cAge = findViewById(R.id.inputAge);
     cPhone = findViewById(R.id.inputPhone);
     cDisease = findViewById(R.id.inputDisease);
     cDoctor = findViewById(R.id.inputDoctor);
     cChannelBtn = findViewById(R.id.channel_btn);
     cShowBtn = findViewById(R.id.appoinment_btn);


     db= FirebaseFirestore.getInstance();
//retrieve data in db
     Bundle bundle = getIntent().getExtras();
     if (bundle != null) {
         cChannelBtn.setText("Update");
         cuId = bundle.getString("cuId");
         cuDate = bundle.getString("cuDate");
         cuName = bundle.getString("cuName");
         cuGender = bundle.getString("cuGender");
         cuAge = bundle.getString("cuAge");
         cuPhone = bundle.getString("cuPhone");
         cuDisease = bundle.getString("cuDisease");
         cuDoctor = bundle.getString("cuDoctor");
         cDate.setText(cuDate);
         cName.setText(cuName);
         cGender.setText(cuGender);
         cAge.setText(cuAge);
         cPhone.setText(cuPhone);
         cDisease.setText(cuDisease);
         cDoctor.setText(cuDoctor);

     }else{
         cChannelBtn.setText("Save");
     }

     cShowBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(ChannelActivity.this ,AppoinmentsActivity.class));
         }
     });





     cChannelBtn.setOnClickListener(new View.OnClickListener(){
         @Override
         public void  onClick(View v) {
             String date = cDate.getText().toString();
             String name = cName.getText().toString();
             String gender = cGender.getText().toString();
             String age = cAge.getText().toString();
             String phone = cPhone.getText().toString();
             String disease = cDisease.getText().toString();
             String doctor = cDoctor.getText().toString();

             Bundle bundle1 = getIntent().getExtras();
             if (bundle1 !=null) {
                 String id = cuId;
                 updateToFireStore(id, date, name, gender, age, phone, disease, doctor);
             }else{
                 String id = UUID.randomUUID().toString();
                 saveToFireStore(id, date, name, gender, age, phone, disease, doctor);
             }

         }
     });
    }

    private void updateToFireStore(String id,String date, String name, String gender, String age, String phone, String disease, String doctor){
        db.collection("Appoinments").document(id).update("date" , date, "name" ,name,"gender" , gender ,"age" ,age , "phone" , phone , "disease" , disease , "doctor" , doctor)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChannelActivity.this, "Data Updated !!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ChannelActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChannelActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveToFireStore(String id, String date, String name, String gender, String age, String phone, String disease, String doctor){

        if (!date.isEmpty() && !name.isEmpty() && !gender.isEmpty() && !age.isEmpty() && !phone.isEmpty()  && !disease.isEmpty() && !doctor.isEmpty()) {
            HashMap<String , Object> map = new HashMap<>();
            map.put("id" , id);
            map.put("date" , date);
            map.put("name" , name);
            map.put("gender" , gender);
            map.put("age" , age);
            map.put("phone", phone);
            map.put("disease", disease);
            map.put("doctor", doctor);

            db.collection("Appoinments").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ChannelActivity.this, "Data Saved !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ChannelActivity.this, "Failed !!", Toast.LENGTH_SHORT).show();
                }
            });

        }else
            Toast.makeText(this, "Empty Fields Not Allowed", Toast.LENGTH_SHORT).show();
    }
}