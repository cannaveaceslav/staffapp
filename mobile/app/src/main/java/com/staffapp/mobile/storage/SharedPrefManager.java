package com.staffapp.mobile.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.staffapp.mobile.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx) {

        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUser(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("firstName", user.getFirstName());
        editor.putString("lastName", user.getLastName());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putString("userRole", user.getUserRole());
        editor.putBoolean("locked", user.getLocked());
        editor.putBoolean("enabled", user.getEnabled());
        editor.putString("plainPassword", user.getPlainPassword());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return !sharedPreferences.getString("email", "").equals("");
    }

    public String getPlainPassword() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("plainPassword","");
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("firstName", ""),
                sharedPreferences.getString("lastName", ""),
                sharedPreferences.getString("email", ""),
                sharedPreferences.getString("password", ""),
                sharedPreferences.getString("userRole", null),
                sharedPreferences.getBoolean("locked", false),
                sharedPreferences.getBoolean("enabled", false),
                sharedPreferences.getString("plainPassword", "")
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
