package com.staffapp.mobile.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.staffapp.mobile.R;
import com.staffapp.mobile.adapter.EmployeeAdapter;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinkEmployeeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LinkEmployeeFragment";
    private RecyclerView recyclerView;
    private List<Employee> employeeList;
    private EmployeeAdapter employeeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.link_employee_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.employee_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllEmployees();

        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    CustomResponse customResponse = response.body();
                    employeeList = (List<Employee>) customResponse.getData().get("employees");
                    employeeAdapter = new EmployeeAdapter(getActivity(), (List<Employee>) employeeList);
                    recyclerView.setAdapter(employeeAdapter);
                    Log.i(TAG, "EmployeeList retrieved");
                } else {
                    Log.i(TAG, "EmployeeList failed");
                }


            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.active_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onClick(View view) {

    }

}
