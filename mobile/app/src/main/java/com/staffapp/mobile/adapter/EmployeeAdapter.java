package com.staffapp.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.internal.LinkedTreeMap;
import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.LinkingActivity;
import com.staffapp.mobile.api.MyAppContext;
import com.staffapp.mobile.fragment.LinkConfirmFragment;
import com.staffapp.mobile.fragment.LinkItemFragment;
import com.staffapp.mobile.model.Employee;
import com.staffapp.mobile.storage.SharedPrefManager;

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
        View rootView;
        Employee employee;


        public EmployeesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            rootView = itemView;


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAbsoluteAdapterPosition();
                    LinkedTreeMap employeeMap = (LinkedTreeMap) employeesList.get(position);
                    String email = (String) employeeMap.get("email");
                    String firstName = (String) employeeMap.get("firstName");
                    String lastName = (String) employeeMap.get("lastName");
                    String name = lastName+" "+firstName;
                    Long id = new Double((Double) employeeMap.get("id")).longValue();

                    SharedPrefManager.getInstance(MyAppContext.getContext()).saveEmployeeId(id);
                    SharedPrefManager.getInstance(MyAppContext.getContext()).saveEmployeeName(name);

                    Toast.makeText(mCtx,  lastName, Toast.LENGTH_SHORT).show();

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new LinkItemFragment();

                    Log.i(TAG, "Clicked EMPLOYEE  :" + id);


                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_check1, myFragment).addToBackStack(null).commit();

                }
            });
        }
    }


}
