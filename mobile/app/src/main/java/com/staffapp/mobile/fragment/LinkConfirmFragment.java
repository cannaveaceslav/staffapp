package com.staffapp.mobile.fragment;


import android.content.Intent;
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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinkConfirmFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LinkConfirmFragment";
    private RecyclerView recyclerView;
    private TextView textViewEmployeeName;
    private TextView textViewItemName;
    private TextView textViewItemBarcode;
    private Long employeeId;
    private Long itemId;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//         employeeId = requireActivity().getIntent().getExtras().getLong("employeeId");
//         itemId = requireActivity().getIntent().getExtras().getLong("itemId");

//        employeeId = this.getArguments().getLong("employeeId");
//        itemId = this.getArguments().getLong("itemId");

//        Log.i(TAG,"EmployeeId: "+employeeId+", ItemId: "+itemId);
        return inflater.inflate(R.layout.link_confirm_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }




    @Override
    public void onClick(View view) {
    }


}
