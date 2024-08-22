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



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ShowActivity activity;
    private List<Model> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    MyAdapter(ShowActivity activity , List<Model> mList){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        Model item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId" , item.getId());
        bundle.putString("uName" , item.getName());
        bundle.putString("uGender" , item.getGender());
        bundle.putString("uAge" , item.getAge());
        bundle.putString("uAddress" , item.getAddress());
        bundle.putString("uEmail" , item.getEmail());
        bundle.putString("uPhone" , item.getPhone());
        Intent intent = new Intent(activity , RegisterActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void deleteData(int position){
       Model item = mList.get(position);
        db.collection("Pregister").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted !!", Toast.LENGTH_SHORT).show();
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
        View v = LayoutInflater.from(activity).inflate(R.layout.item , parent , false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(mList.get(position).getName());
        holder.gender.setText(mList.get(position).getGender());
        holder.age.setText(mList.get(position).getAge());
        holder.address.setText(mList.get(position).getAddress());
        holder.email.setText(mList.get(position).getEmail());
        holder.phone.setText(mList.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, gender, age, address, email, phone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            gender =itemView.findViewById(R.id.gender_text);
            age =itemView.findViewById(R.id.age_text);
            address =itemView.findViewById(R.id.address_text);
            email =itemView.findViewById(R.id.email_text);
            phone =itemView.findViewById(R.id.phone_text);
        }
    }
}



