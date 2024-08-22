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

public class VMyAdapter extends RecyclerView.Adapter <VMyAdapter.MyViewHolder> {

    private com.example.suwa_arana.VShowActivity activity;
    private List<com.example.suwa_arana.VModel> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public VMyAdapter(com.example.suwa_arana.VShowActivity activity, List<com.example.suwa_arana.VModel> mList){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        com.example.suwa_arana.VModel item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uName", item.getName());
        bundle.putString("uPhone", item.getPhone());
        bundle.putString("uAddress", item.getAddress());
        bundle.putString("uDistrict", item.getDistrict());
        bundle.putString("uNic", item.getNic());
        bundle.putString("uDose", item.getDose());
        bundle.putString("uAllergie", item.getAllergie());

        Intent intent = new Intent(activity, Registration.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);

    }

    public void deleteData(int position){
        com.example.suwa_arana.VModel item = mList.get(position);
        db.collection("Vaccine").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data deleted Successfully!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.vitem , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.phone.setText(mList.get(position).getPhone());
        holder.address.setText(mList.get(position).getAddress());
        holder.district.setText(mList.get(position).getDistrict());
        holder.nic.setText(mList.get(position).getNic());
        holder.dose.setText(mList.get(position).getDose());
        holder.allergie.setText(mList.get(position).getAllergie());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, phone, address, district, nic, dose, allergie;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.et_vname);
            phone = itemView.findViewById(R.id.et_vphone);
            address = itemView.findViewById(R.id.et_vadd);
            district = itemView.findViewById(R.id.et_vdis);
            nic = itemView.findViewById(R.id.et_nic);
            dose = itemView.findViewById(R.id.et_vdose);
            allergie = itemView.findViewById(R.id.et_alldis);
        }
    }
}
