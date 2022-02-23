package com.staffapp.mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.staffapp.mobile.R;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.LoginUser;
import com.staffapp.mobile.model.User;
import com.staffapp.mobile.storage.SharedPrefManager;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, CheckActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email is not valid");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 characters long");
            editTextPassword.requestFocus();
            return;
        }

        String base = email + ":" + password;
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        LoginUser loginUser = new LoginUser(email, password);


        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .validateUser(authHeader,loginUser);


        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    CustomResponse customResponse = response.body();
                    assert customResponse != null;
                    Map<?, ?> userMap = (Map<?, ?>) customResponse.getData().get("user");
                    assert userMap != null;
                    User user = new User(
                            (String) userMap.get("firstName"),
                            (String) userMap.get("lastName"),
                            (String) userMap.get("email"),
                            (String) userMap.get("password"),
                            (String) userMap.get("userRole"),
                            (Boolean) userMap.get("locked"),
                            (Boolean) userMap.get("enabled")
                    );

                    Log.i(TAG, user + "User retrieved");




                    SharedPrefManager.getInstance((LoginActivity.this))
                            .saveUser(user);

                    Intent intent = new Intent(LoginActivity.this, CheckActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed. Check connection", Toast.LENGTH_LONG).show();

            }

        });

    }

    @Override
    public void onClick(View view) {
        userLogin();
    }


}