package com.staffapp.mobile.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.staffapp.mobile.R;

public class InternetProblemsDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setCancelable(false);
        ProgressDialog.Builder progressBuilder = new ProgressDialog.Builder(getActivity());


        builder.setMessage(R.string.dialog_connection_lost)
                .setPositiveButton(R.string.retry, (dialog, id) -> {

                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                });
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> {
                Boolean wantToCloseDialog = false;
                if (wantToCloseDialog)
                    dismiss();
            });
        }
    }
}