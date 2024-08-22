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

public class Registration extends AppCompatActivity {

    private EditText et_vname, et_vphone, et_vadd, et_vdis, et_nic, et_vdose, et_alldis;
    private Button btn_vregister, btn_view;
    private FirebaseFirestore db;

    private String uName, uPhone, uAddress, uDistrict, uNic, uDose, uAllergie, uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        et_vname = findViewById(R.id.et_vname);
        et_vphone = findViewById(R.id.et_vphone);
        et_vadd = findViewById(R.id.et_vadd);
        et_vdis = findViewById(R.id.et_vdis);
        et_nic = findViewById(R.id.et_nic);
        et_vdose = findViewById(R.id.et_vdose);
        et_alldis = findViewById(R.id.et_alldis);
        btn_vregister = findViewById(R.id.btn_vregister);
        btn_view = findViewById(R.id.btn_view);

        db = FirebaseFirestore.getInstance();


        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            btn_vregister.setText("Update");
            uName = bundle.getString("uName");
            uId = bundle.getString("uId");
            uPhone = bundle.getString("uPhone");
            uAddress = bundle.getString("uAddress");
            uDistrict = bundle.getString("uDistrict");
            uNic = bundle.getString("uNic");
            uDose = bundle.getString("uDose");
            uAllergie = bundle.getString("uAllergie");

            et_vname.setText(uName);
            et_vphone.setText(uPhone);
            et_vadd.setText(uAddress);
            et_vdis.setText(uDistrict);
            et_nic.setText(uNic);
            et_vdose.setText(uDose);
            et_alldis.setText(uAllergie);


        }else{
            btn_vregister.setText("Register");

        }

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, com.example.suwa_arana.VShowActivity.class));
            }
        });

        btn_vregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_vname.getText().toString();
                String phone = et_vphone.getText().toString();
                String address = et_vadd.getText().toString();
                String district = et_vdis.getText().toString();
                String nic = et_nic.getText().toString();
                String dose = et_vdose.getText().toString();
                String allergie = et_alldis.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if(bundle1 !=null){
                    String id = uId;
                    updateToFireStore(id, name, phone, address, district, nic, dose, allergie);

                }else{
                    String id = UUID.randomUUID().toString();

                    saveToFirestore(id, name, phone, address, district, nic, dose, allergie);

                }

            }
        });

    }

    private void updateToFireStore(String id, String name, String phone, String address, String district, String nic, String dose, String allergie){
        db.collection("Vaccine").document(id).update("name", name,"phone", phone, "address", address, "district",district,"nic",nic,"dose",dose,"allergie",allergie)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registration.this, "Data Updated!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Registration.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registration.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToFirestore(String id, String name, String phone, String address, String district, String nic, String dose, String allergie){
        if(!name.isEmpty()&& !phone.isEmpty() && !address.isEmpty()&& !district.isEmpty()&& !nic.isEmpty()&& !dose.isEmpty()&& !allergie.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("phone", phone);
            map.put("address", address);
            map.put("district", district);
            map.put("nic", nic);
            map.put("dose", dose);
            map.put("allergie", allergie);

            db.collection("Vaccine").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registration.this, "Data saved!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Registration.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            });

        }else
            Toast.makeText(this, "Empty fields not Allowed", Toast.LENGTH_SHORT).show();
    }
}