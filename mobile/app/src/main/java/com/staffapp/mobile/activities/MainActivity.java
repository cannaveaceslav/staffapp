package com.staffapp.mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.staffapp.mobile.R;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText editTextBarcode;
    private TextView textViewDescription;
    private TextView textViewOwner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_fragment);

        editTextBarcode = findViewById(R.id.barcode);
        textViewDescription = findViewById(R.id.description2);
        textViewOwner = findViewById(R.id.owner);

        MaterialButton check_button = (MaterialButton) findViewById(R.id.check_button);

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllItems();

        call.enqueue(new Callback<CustomResponse>() {

            @Override
            public void onResponse(@NonNull Call<CustomResponse> call, @NonNull Response<CustomResponse> response) {

                CustomResponse customResponse = response.body();
                assert customResponse != null;
                String msg = customResponse.getMessage();
                String status = customResponse.getStatus();
                String timestamp = customResponse.getTimeStamp();
                List<?> itemList = (List<?>) customResponse.getData().get("items");
                System.out.println(msg + " | " + status + "  | " + timestamp);
                assert itemList != null;
                System.out.println("[itemList  SIZE ]" + itemList.size());
                Log.i(TAG, itemList + "List with items retrieved");
                Toast.makeText(MainActivity.this, customResponse.getMessage(), Toast.LENGTH_LONG).show();

//                if (itemList.size() > 0) {
//                    JSONObject jsonObject = null;
//                    JSONArray jsonArray = new JSONArray(itemList);
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        try {
////                            jsonObject = (JSONObject) jsonArray.get(i);
////                            System.out.println("/****************************/");
////                            System.out.println(jsonObject);
////
////                            Long id = jsonObject.getLong("id");
////                            String itemName = jsonObject.getString("itemName");
////                            String barcode = jsonObject.getString("barcode");
////
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


//        check_button.setOnClickListener((view -> {
////            startActivity(new Intent(this, LoginActivity.class));
//            checkBarcode(view);
//        }));

        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SSSSSSSSSS", Toast.LENGTH_LONG).show();

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }



    public void checkBarcode(View view) {
        Log.i(TAG,  "HHHHHHHHHHHHHHHHHHHHHHHHHHUI");
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG,  "ZZZZZZZZZZZZZZZZZZZ");
    }
}