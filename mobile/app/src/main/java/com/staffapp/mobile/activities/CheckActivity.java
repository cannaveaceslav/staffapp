package com.staffapp.mobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.staffapp.mobile.R;
import com.staffapp.mobile.model.User;
import com.staffapp.mobile.storage.SharedPrefManager;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        EditText editItem = findViewById(R.id.barcode);
        TextView itemDescriptionView = findViewById(R.id.description);
        TextView ownerView = findViewById(R.id.owner);

        User user = SharedPrefManager.getInstance(this).getUser();
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
}