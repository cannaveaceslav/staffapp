package com.staffapp.mobile.api;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import androidx.constraintlayout.motion.widget.Key;

import com.staffapp.mobile.model.User;
import com.staffapp.mobile.storage.SharedPrefManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.spec.SecretKeySpec;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


//    private static final String AUTH = "Basic " + Base64.encodeToString(("admin:admin").getBytes(), Base64.NO_WRAP);
//    public static final String BASE_URL = "http://192.168.0.6:8080";
    public static final String BASE_URL = "http://138.3.240.127:8082";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private static final String TAG = "RetrofitClient";


    private RetrofitClient() {
        User user = SharedPrefManager.getInstance(MyAppContext.getContext()).getUser();
        Log.i(TAG, user + "User retrieved from context");

        String email = user.getEmail();

        String password = user.getPlainPassword();

        String role = user.getUserRole();

        String base = email + ":" + password;
        String AUTH = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

//        Log.i(TAG,base);
//        Log.i(TAG,role);



        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                if (original.header("Authorization") == null) {
                                    Request.Builder requestBuilder = original.newBuilder()
                                            .addHeader("Authorization", AUTH)
                                            .method(original.method(), original.body());
                                    Request request = requestBuilder.build();
                                    Log.i(TAG, "Request intercepted");
                                    Log.i(TAG, BASE_URL);
                                    return chain.proceed(request);
                                } else {
                                    return chain.proceed(original);
                                }

                            }
                        }
                ).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;

    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }


}
