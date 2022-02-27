package com.staffapp.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.CheckActivity;
import com.staffapp.mobile.activities.LoginActivity;
import com.staffapp.mobile.storage.SharedPrefManager;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SettingsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.logout_botton).setOnClickListener(this);

    }

    private void logoutUser() {
        SharedPrefManager.getInstance(getActivity()).clear();
        Log.i(TAG,  "Logout clicked");
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
