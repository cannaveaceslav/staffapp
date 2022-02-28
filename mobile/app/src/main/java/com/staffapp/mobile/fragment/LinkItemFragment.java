package com.staffapp.mobile.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.staffapp.mobile.R;
import com.staffapp.mobile.adapter.EmployeeAdapter;
import com.staffapp.mobile.adapter.ItemAdapter;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.Employee;
import com.staffapp.mobile.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinkItemFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "LinkItemFragment";
    private RecyclerView recyclerView;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.link_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.item_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllItems();

        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    CustomResponse customResponse = response.body();
                    itemList = (List<Item>) customResponse.getData().get("items");
                    itemAdapter = new ItemAdapter(getActivity(), (List<Item>) itemList);
                    recyclerView.setAdapter(itemAdapter);
                    Log.i(TAG, "Items retrieved. ParentActivity: "+ getActivity().toString());
                } else {
                    Log.i(TAG, "ItemList failed");
                }


            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View view) {

    }

}
