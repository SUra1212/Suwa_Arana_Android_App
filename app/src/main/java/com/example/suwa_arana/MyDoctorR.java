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

public class MyDoctorR extends RecyclerView.Adapter<MyDoctorR.MyviewHolder> {
    private showDoctor activity;
    private List<ModelD> mList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MyDoctorR(showDoctor activity , List<ModelD> mList ){
        this.activity = activity;
        this.mList = mList;
    }

    public void updateData(int position){
        ModelD item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uName", item.getName());
        bundle.putString("uLicense", item.getLicense());
        bundle.putString("uEmail", item.getEmail());
        bundle.putString("uGender", item.getGender());
        bundle.putString("uPnumber", item.getPnumber());
        bundle.putString("uSpeciality", item.getSpeciality());
        bundle.putString("uLanguage", item.getLanguage());
        Intent intent = new Intent(activity, RegisterForm.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    public void deleteData(int position){
        ModelD item = mList.get(position);
        db.collection("DoctorR").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted", Toast.LENGTH_SHORT).show();
                    }else{
                            Toast.makeText(activity, "Error" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
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
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(activity).inflate(R.layout.ditem, parent, false);
       return  new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
          holder.name.setText(mList.get(position).getName());
        holder.license.setText(mList.get(position).getLicense());
        holder.email.setText(mList.get(position).getEmail());
        holder.gender.setText(mList.get(position).getGender());
        holder.pnumber.setText(mList.get(position).getPnumber());
        holder.speciality.setText(mList.get(position).getSpeciality());
        holder.language.setText(mList.get(position).getLanguage());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView name, license, email, gender, pnumber, speciality, language;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.et_name);
            license = itemView.findViewById(R.id.et_license);
            email = itemView.findViewById(R.id.et_email);
            gender = itemView.findViewById(R.id.et_gender);
            pnumber = itemView.findViewById(R.id.et_pnumber);
            speciality = itemView.findViewById(R.id.et_speciality);
            language = itemView.findViewById(R.id.et_language);
        }
    }
}
