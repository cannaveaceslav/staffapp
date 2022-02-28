package com.staffapp.mobile.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.LinkingActivity;
import com.staffapp.mobile.adapter.EmployeeAdapter;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinkConfirmFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LinkEmployeeFragment";
    private RecyclerView recyclerView;
    private TextView textViewEmployeeName;
    private TextView textViewItemName;
    private TextView textViewItemBarcode;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.link_confirm_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void linkItemToEmployee() {
        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .linkEmployee(4l,4l);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Linking successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Linking failed", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        linkItemToEmployee();
    }


}
