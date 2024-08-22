package com.example.suwa_arana;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class showDoctor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private MyDoctorR adapter;
    private List<ModelD> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new MyDoctorR(this,list);
        recyclerView.setAdapter(adapter);



        ItemTouchHelper touchHelper = new ItemTouchHelper(new DRhelper(adapter) );
        touchHelper.attachToRecyclerView(recyclerView);
        showData();
    }

    public void showData(){
        db.collection("DoctorR").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for (DocumentSnapshot snapshot : task.getResult()){

                            ModelD modeld = new ModelD(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("license"), snapshot.getString("email"), snapshot.getString("gender"), snapshot.getString("pnumber"), snapshot.getString("speciality"), snapshot.getString("language"));
                            list.add(modeld);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(showDoctor.this, "Ooops.....", Toast.LENGTH_SHORT).show();

            }
        });

    }
}


















