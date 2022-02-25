package com.staffapp.mobile.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.staffapp.mobile.R;
import com.staffapp.mobile.model.User;
import com.staffapp.mobile.storage.SharedPrefManager;

public class CheckFragment extends Fragment {

    private EditText barcode;
    private TextView description;
    private TextView owner;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barcode = view.findViewById(R.id.barcode);
        description = view.findViewById(R.id.description);
        owner = view.findViewById(R.id.owner);

        description.setText(SharedPrefManager.getInstance(getActivity()).getUser().getUserRole());
        owner.setText(SharedPrefManager.getInstance(getActivity()).getUser().getLastName());
    }
}
