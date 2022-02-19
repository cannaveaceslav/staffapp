package com.staffapp.mobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staffapp.mobile.adapter.InterfaceAdapter;
import com.staffapp.mobile.model.CustomResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton login_button = (MaterialButton) findViewById(R.id.login_button);

//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(CustomResponse.class, new InterfaceAdapter());
//        Gson gson = builder.create();

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllItems();

        call.enqueue(new Callback<CustomResponse>() {

            @Override
            public void onResponse(@NonNull Call<CustomResponse> call, @NonNull Response<CustomResponse> response) {


                assert response.body() != null;
                List<Object> itemList = (List<Object>) response.body().getData().get("items");
                System.out.println("[itemList   ]" + itemList);
                Log.i(TAG, itemList + "Hello from logs");
                assert itemList != null;
                Toast.makeText(MainActivity.this, itemList.toString(), Toast.LENGTH_LONG).show();

                if (itemList.size() > 0) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(itemList.get(0).toString());
                        Long id = jsonObject.getLong("id");
                        String itemName = jsonObject.getString("itemName");
                        System.out.println("ID: [" + id + "], itemName: [" + itemName + "]");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        login_button.setOnClickListener((view -> {

//            Call<CustomResponse> call2 =  RetrofitClient
//                    .getInstance()
//                    .getApi()
//                    .linkEmployee(2L, 2L);
//            call2.enqueue(new Callback<CustomResponse>() {
//                @Override
//                public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
//                    Log.i(TAG,"Success");
//                }
//
//                @Override
//                public void onFailure(Call<CustomResponse> call, Throwable t) {
//                    Log.i(TAG,"Fail");
//                }
//            });

            if (username.getText().toString().equals("admin") &&
                    password.getText().toString().equals("admin")) {


                Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            }

        }));


    }
}