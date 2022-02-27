package com.staffapp.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;
import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.ItemsActivity;
import com.staffapp.mobile.model.Employee;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeesViewHolder> {
    private static final String TAG = "EmployeeAdapter";

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

        LinkedTreeMap employeeMap = (LinkedTreeMap) employeesList.get(position);
        Employee employee = new Employee(
                 new Double((Double) employeeMap.get("id")).longValue(),
                (String) employeeMap.get("firstName".toString()),
                (String) employeeMap.get("lastName"),
                (String) employeeMap.get("email")
        );

        String fullName = employee.getLastName()+" "+employee.getFirstName();

        holder.textViewLastName.setText(fullName);
        holder.textViewEmail.setText(employee.getEmail());


    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }



    class EmployeesViewHolder extends RecyclerView.ViewHolder{
        TextView textViewLastName;
        TextView textViewEmail;

        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                int positionIndex = getBindingAdapterPosition();
                Toast.makeText(mCtx, "Was clicked "+ positionIndex, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mCtx, ItemsActivity.class);
                    mCtx.startActivity(intent);
                }
            });
        }
    }


}
