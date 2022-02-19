package com.staffapp.mobile;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staffapp.mobile.adapter.InterfaceAdapter;
import com.staffapp.mobile.model.CustomResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //    public static final String BASE_URL = "http://localhost:8080";
    private static final String AUTH = "Basic " + Base64.encodeToString(("admin:admin").getBytes(), Base64.NO_WRAP);
    public static final String BASE_URL = "http://192.168.0.8:8080";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();

                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", AUTH)
                                        .method(original.method(), original.body());
                                Request request = requestBuilder.build();
                                return chain.proceed(request);

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
