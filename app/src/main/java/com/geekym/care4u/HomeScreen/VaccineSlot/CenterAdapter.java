package com.geekym.care4u.HomeScreen.VaccineSlot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekym.care4u.R;

import java.util.ArrayList;

public class CenterAdapter extends RecyclerView.Adapter<CenterAdapter.ViewHolder> {

    private ArrayList<CenterModel> Centers = new ArrayList<>();


    public CenterAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CenterAdapter.ViewHolder holder, int position) {
        CenterModel Center = Centers.get(position);
        holder.CenterName.setText(Center.getCenterName());
        holder.CenterLocation.setText(Center.getCenterLocation());
        holder.VaccineTime.setText("From " + Center.getCenterFromTime() + " To " + Center.getCenterEndTime());
        holder.VaccineName.setText(Center.getVaccineName());
        holder.AgeLimit.setText("Age Limit " + Center.getAgeLimit());
        holder.FeeType.setText(Center.getFeeType());
        holder.VaccineAvalibility.setText("Availability " + Center.getAvailability());
        holder.Dose1.setText("Dose1 : " + Center.getDose1());
        holder.Dose2.setText("Dose2 : " + Center.getDose2());

    }

    @Override
    public int getItemCount() {
        return Centers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView CenterName, CenterLocation, VaccineTime, VaccineName, FeeType, AgeLimit, VaccineAvalibility, Dose1, Dose2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CenterName = itemView.findViewById(R.id.CenterName);
            CenterLocation = itemView.findViewById(R.id.CenterLocation);
            VaccineTime = itemView.findViewById(R.id.VaccineTiming);
            VaccineName = itemView.findViewById(R.id.VaccineName);
            FeeType = itemView.findViewById(R.id.FeeType);
            AgeLimit = itemView.findViewById(R.id.AgeLimit);
            VaccineAvalibility = itemView.findViewById(R.id.VaccineAvailability);
            Dose1 = itemView.findViewById(R.id.VaccineAvailabilityDose1);
            Dose2 = itemView.findViewById(R.id.VaccineAvailabilityDose2);


        }
    }


    public void setCenters(ArrayList<CenterModel> centers) {

        Centers = centers;
        notifyDataSetChanged();
    }
}
