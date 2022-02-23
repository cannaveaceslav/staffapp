package com.staffapp.mobile.api;



import com.staffapp.mobile.model.CustomResponse;
import com.staffapp.mobile.model.LoginUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @POST("/login/validate")
    Call<CustomResponse> validateUser(
            @Header("Authorization") String authHeader,
            @Body LoginUser loginUser
    );

    @GET("/login/validateLogin")
    Call<Boolean> loginUser(@Header("Authorization") String authHeader);
}
