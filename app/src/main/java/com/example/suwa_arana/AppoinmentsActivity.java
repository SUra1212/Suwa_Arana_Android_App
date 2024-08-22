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

public class AppoinmentsActivity extends AppCompatActivity {
//variable declaration
   private RecyclerView recyclerView;
   private FirebaseFirestore db;
   private MycAdapter adapter;
   private List<Cmodel> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinments);

        recyclerView = findViewById(R.id.crecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db= FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adapter = new MycAdapter(this , list);
        recyclerView.setAdapter(adapter);

       ItemTouchHelper touchHelper = new ItemTouchHelper(new cTouchHelper(adapter));
       touchHelper.attachToRecyclerView(recyclerView);
       showData();


    }

    public void showData(){
       db.collection("Appoinments").get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       list.clear();
                       for (DocumentSnapshot snapshot : task.getResult()){
                           Cmodel cmodel = new Cmodel(snapshot.getString("id") , snapshot.getString("date") ,snapshot.getString("name") , snapshot.getString("gender") , snapshot.getString("age") , snapshot.getString("phone") , snapshot.getString("disease") , snapshot.getString("doctor"));

                           list.add(cmodel);
                       }
                        adapter.notifyDataSetChanged();

                   }
               }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(AppoinmentsActivity.this, "Oops...Something went wrong.", Toast.LENGTH_SHORT).show();
           }
       });

    }
}