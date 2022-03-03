package com.staffapp.mobile.api;

import android.app.Application;

public class MyAppContext extends Application {
    private static MyAppContext instance;

    public static MyAppContext getInstance() {
        return instance;
    }

    public static MyAppContext getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }


}
