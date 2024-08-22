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

public class VShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private VMyAdapter adapter;
    private List<com.example.suwa_arana.VModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vshow);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();

        list = new ArrayList<>();
        adapter = new VMyAdapter(this, list);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new com.example.suwa_arana.VTouchHelper(adapter));
        touchHelper.attachToRecyclerView(recyclerView);
        showData();
    }

    public void showData(){

        db.collection("Vaccine").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot snapshot : task.getResult()){

                            com.example.suwa_arana.VModel model = new com.example.suwa_arana.VModel(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("phone"),
                                    snapshot.getString("address"), snapshot.getString("district"), snapshot.getString("nic"),
                                    snapshot.getString("dose"), snapshot.getString("allergie"));
                            list.add(model);

                        }
                        adapter.notifyDataSetChanged();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VShowActivity.this, "Oops! something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}