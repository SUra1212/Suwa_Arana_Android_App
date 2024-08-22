package com.example.suwa_arana;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MycAdapter extends RecyclerView.Adapter<MycAdapter.MycViewHolder> {
    private AppoinmentsActivity activity;
    private List<Cmodel>cList;
    private FirebaseFirestore db =FirebaseFirestore.getInstance();

    MycAdapter(AppoinmentsActivity activity, List<Cmodel> cList){
        this.activity = activity;
        this.cList = cList;
    }

    public void updateData(int position){
        Cmodel item = cList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("cuId" , item.getId());
        bundle.putString("cuDate" , item.getDate());
        bundle.putString("cuName" , item.getName());
        bundle.putString("cuGender" , item.getGender());
        bundle.putString("cuAge" , item.getAge());
        bundle.putString("cuPhone" , item.getPhone());
        bundle.putString("cuDisease" , item.getDisease());
        bundle.putString("cuDoctor" , item.getDoctor());
        Intent intent = new Intent(activity , ChannelActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);

    }

    public void  deleteData(int position){
        Cmodel item = cList.get(position);
        db.collection("Appoinments").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted !!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(activity, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        cList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }
    @NonNull
    @Override
    public MycViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.citem , parent , false);
                return new MycViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MycViewHolder holder, int position) {
        holder.date.setText(cList.get(position).getDate());
        holder.name.setText(cList.get(position).getName());
        holder.gender.setText(cList.get(position).getGender());
        holder.age.setText(cList.get(position).getAge());
        holder.phone.setText(cList.get(position).getPhone());
        holder.disease.setText(cList.get(position).getDisease());
        holder.doctor.setText(cList.get(position).getDoctor());
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    public static class MycViewHolder extends RecyclerView.ViewHolder{

        TextView date, name, gender, age, phone, disease, doctor;
        public MycViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date_text);
            name = itemView.findViewById(R.id.name_text);
            gender = itemView.findViewById(R.id.gender_text);
            age = itemView.findViewById(R.id.age_text);
            phone = itemView.findViewById(R.id.phone_text);
            disease = itemView.findViewById(R.id.disease_text);
            doctor = itemView.findViewById(R.id.doctor_text);
        }
    }
}
