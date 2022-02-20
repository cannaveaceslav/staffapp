package com.staffapp.mobile.api;


import android.util.Base64;

import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Api {

    @GET("/items")
    Call<CustomResponse> getAllItems();

    @FormUrlEncoded
    @POST("/items/save")
    Call<CustomResponse> linkEmployee(
            @Field("employeeId") Long employeeId,
            @Field("itemId") Long itemId
    );



    @FormUrlEncoded
    @POST("/login/validate")
    Call<CustomResponse> validateUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/login/validateLogin")
    Call<Boolean> loginUser(@Header("Authorization") String authHeader);
}
