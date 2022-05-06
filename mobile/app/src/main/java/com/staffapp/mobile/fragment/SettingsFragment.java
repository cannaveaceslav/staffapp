package com.staffapp.mobile.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.LoginActivity;
import com.staffapp.mobile.storage.SharedPrefManager;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SettingsFragment";
    private TextView editTextEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.logout_botton).setOnClickListener(this);
        editTextEmail =  view.findViewById(R.id.editTextEmail);
        editTextEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());

    }

    private void logoutUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setMessage("Do you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPrefManager.getInstance(getActivity()).clear();
                Log.i(TAG,  "Logout clicked");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog ad = builder.create();
        ad.show();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_botton:
                logoutUser();
                break;

        }
    }
}
