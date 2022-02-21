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

    private EditText editItem;
    private TextView itemDescriptionView;
    private TextView ownerView;
    private TextView userView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        editItem = findViewById(R.id.barcode);
        itemDescriptionView = findViewById(R.id.description);
        ownerView = findViewById(R.id.owner);
        userView = findViewById(R.id.user);

        User user = SharedPrefManager.getInstance(this).getUser();
        userView.setText(user.getEmail());
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