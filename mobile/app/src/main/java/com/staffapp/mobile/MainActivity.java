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

import java.util.List;

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
                String items = response.body().toString();
                List<Object> itemList = (List<Object>) response.body().getData().get("items");
                String headers = response.headers().toString();
                assert itemList != null;
                int message = itemList.size();
                System.out.println("[TIEMS    ]"+items);
                System.out.println("[HEADERS   ]"+headers);
                System.out.println("[List size   ]"+message);
                System.out.println("[itemList   ]"+itemList);
                Log.i(TAG,items+"Hello from logs");
                Toast.makeText(MainActivity.this,  items, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<CustomResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        login_button.setOnClickListener((view -> {

            if (username.getText().toString().equals("admin") &&
                    password.getText().toString().equals("admin"))
                Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(MainActivity.this, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
            }

        }));


    }
}