package com.staffapp.mobile.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.internal.LinkedTreeMap;
import com.staffapp.mobile.R;
import com.staffapp.mobile.api.ConnectionLiveData;
import com.staffapp.mobile.api.MyAppContext;
import com.staffapp.mobile.api.RetrofitClient;
import com.staffapp.mobile.fragment.CheckFragment;
import com.staffapp.mobile.fragment.InternetProblemsDialogFragment;
import com.staffapp.mobile.fragment.LinkEmployeeFragment;
import com.staffapp.mobile.fragment.SettingsFragment;
import com.staffapp.mobile.fragment.UnlinkFragment;
import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.storage.SharedPrefManager;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinkingActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "LinkingActivity";
    private EditText editTextBarcode;
    private TextView textViewItemName;
    private TextView textViewDescription;
    private TextView textViewOwner;
    LinkedTreeMap itemMap;
    LinkedTreeMap employeeMap;
    CustomResponse customResponse;
    String itemName = "";
    String itemDescription = "";
    String employeeFirstName = "";
    String employeeLastName = "";
    String employeeFullName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

//        editTextBarcode = findViewById(R.id.barcode);
//        textViewItemName = findViewById(R.id.itemName);
//        textViewDescription = findViewById(R.id.description2);
//        textViewOwner = findViewById(R.id.owner);

        DialogFragment noInternetDialogFragment = new InternetProblemsDialogFragment();
        getSupportFragmentManager().beginTransaction()
                .add(noInternetDialogFragment, "INTERNET_CONNECTION");
        noInternetDialogFragment.setCancelable(false);

        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, connection -> {

            if (connection.getIsConnected()) {
                switch (connection.getType()) {
                    case 1:
                        noInternetDialogFragment.dismiss();
                        Toast.makeText(LinkingActivity.this, "Wifi turned ON", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        noInternetDialogFragment.dismiss();
                        Toast.makeText(LinkingActivity.this, "Mobile data turned ON", Toast.LENGTH_SHORT).show();
                        break;
                }
            } else {
                noInternetDialogFragment.show(getSupportFragmentManager(), "INTERNET_CONNECTION");
                Toast.makeText(LinkingActivity.this, "Connections turned OFF", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView navigationView = findViewById(R.id.botton_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
        displayFragment(new CheckFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.active_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_check, fragment)
                .commit();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAllEmployees();
        call.enqueue(new Callback<CustomResponse>() {

            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    CustomResponse customResponse = response.body();
                    String msg = customResponse.getMessage();
                    String status = customResponse.getStatus();
                    String timestamp = customResponse.getTimeStamp();
                    List<?> employeeList = (List<?>) customResponse.getData().get("employees");
                    System.out.println(msg + " | " + status + "  | " + timestamp);
                    assert employeeList != null;
//                    Log.i(TAG, employeeList + "List with items retrieved");
                    Toast.makeText(LinkingActivity.this, customResponse.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LinkingActivity.this, "Failed to retrieve employees", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {

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


    private void linkItemToEmployee() {
        Long employeeId = SharedPrefManager.getInstance(MyAppContext.getContext()).getEmployeeId();
        Long itemId = SharedPrefManager.getInstance(MyAppContext.getContext()).getItemId();
        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .linkEmployee(employeeId, itemId);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LinkingActivity.this, "Linking successful", Toast.LENGTH_LONG).show();
                    displayFragment(new LinkEmployeeFragment());
                } else {
                    Toast.makeText(LinkingActivity.this, "Linking failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
            }
        });

    }


    public void onClick(View view) {
        linkItemToEmployee();
    }

    public void onClickCheckButton(View view) {
        Log.i(TAG, "Entered into onClickCheckButton()");
        checkBarcode();
    }

    private void checkBarcode() {
        editTextBarcode = findViewById(R.id.barcode);
        textViewItemName = findViewById(R.id.itemName);
        textViewDescription = findViewById(R.id.description2);
        textViewOwner = findViewById(R.id.owner);
        String barcode = editTextBarcode.getText().toString().trim();

        Call<CustomResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getItemByBarcode(barcode);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                customResponse = response.body();
                itemMap = (LinkedTreeMap) customResponse.getData().get("item");

                if (response.isSuccessful() && itemMap != null && itemMap.size() > 0) {
                    //getting item`s Info
                    Log.i(TAG, "Item retrieved. " + itemMap.get("itemName"));
                    itemName = (String) itemMap.get("itemName");
                    itemDescription = (String) itemMap.get("description");

                    //getting employee Info
                    employeeMap = (LinkedTreeMap) itemMap.get("employee");

                    if (employeeMap != null && employeeMap.size() > 0) {
                        Log.i(TAG, "Item has employee:  retrieved. " + employeeMap.get("lastName"));
                        employeeFirstName = (String) employeeMap.get("firstName");
                        employeeLastName = (String) employeeMap.get("lastName");
                        employeeFullName = employeeLastName + " " + employeeFirstName;
                    } else {
                        Log.i(TAG, "Item has NO employee!");
                        employeeFullName = "";
                    }

                    editTextBarcode.getText().clear();
                    textViewItemName.setText(itemName);
                    textViewDescription.setText(itemDescription);
                    textViewOwner.setText(employeeFullName);
                } else {
                    Log.i(TAG, "Wrong barcode " + this.getClass());
                    editTextBarcode.getText().clear();
                    textViewItemName.setText("");
                    textViewDescription.setText("");
                    textViewOwner.setText("");
                    Toast.makeText(LinkingActivity.this, "Wrong barcode. Try again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                Toast.makeText(LinkingActivity.this, "Item not found. Try again", Toast.LENGTH_LONG).show();
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_check:
                fragment = new CheckFragment();
                break;
            case R.id.menu_link:
                fragment = new LinkEmployeeFragment();
                break;
            case R.id.menu_unlink:
                fragment = new UnlinkFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;
        }
        if (fragment != null) {
            displayFragment(fragment);
        }
        return false;
    }
}