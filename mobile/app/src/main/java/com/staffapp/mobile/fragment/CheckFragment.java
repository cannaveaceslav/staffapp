package com.staffapp.mobile.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.staffapp.mobile.R;
import com.staffapp.mobile.activities.MainActivity;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.User;
import com.staffapp.mobile.storage.SharedPrefManager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CheckFragment";
    private EditText editTextBarcode;
    private TextView textViewItemName;
    private TextView textViewDescription;
    private TextView textViewOwner;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_fragment, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextBarcode = view.findViewById(R.id.barcode);
        textViewItemName = view.findViewById(R.id.itemName);
        textViewDescription = view.findViewById(R.id.description2);
        textViewOwner = view.findViewById(R.id.owner);




    }

    private void checkBarcode() {

        String barcode = editTextBarcode.getText().toString().trim();

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getItemByBarcode(barcode);

        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    CustomResponse customResponse = response.body();
                    assert customResponse != null;
                    Map<?, ?> itemsMap = (Map<?, ?>) customResponse.getData().get("item");
                    assert itemsMap != null;

                    editTextBarcode.setText("scan barcode");
                    textViewDescription.setText("item test test");
                    textViewOwner.setText("Slava best");
                    Log.i(TAG, "Item retrieved. "+ getActivity().toString());
                } else {
                    Log.i(TAG, "Wrong barcode "+ getActivity().toString());
                    Toast.makeText(getActivity(), "Wrong barcode Try again", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Item not found. Try again", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        checkBarcode();
    }
}
