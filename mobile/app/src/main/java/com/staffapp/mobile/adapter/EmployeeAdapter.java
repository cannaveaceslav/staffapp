package com.staffapp.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;
import com.staffapp.mobile.R;
import com.staffapp.mobile.model.Employee;

import java.util.HashMap;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeesViewHolder> {

    private Context mCtx;
    private List<?> employeesList;

    public EmployeeAdapter(Context mCtx, List<Employee> employeesList) {
        this.mCtx = mCtx;
        this.employeesList = employeesList;
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_employees, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeesViewHolder holder, int position) {
//        Employee employee = (Employee) employeesList.get(position);
        LinkedTreeMap employeeMap = (LinkedTreeMap) employeesList.get(position);
        Employee employee = new Employee(
                (String) employeeMap.get("firstName".toString()),
                (String) employeeMap.get("lastName")
        );

        holder.textViewLastName.setText(employee.getLastName());
        holder.textViewFirstName.setText(employee.getFirstName());


    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    class EmployeesViewHolder extends RecyclerView.ViewHolder{
        TextView textViewLastName;
        TextView textViewFirstName;

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewFirstName = itemView.findViewById(R.id.textViewFirstName);
        }
    }


}
