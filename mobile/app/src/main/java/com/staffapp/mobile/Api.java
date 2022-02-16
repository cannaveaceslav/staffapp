package com.staffapp.mobile;

import com.staffapp.mobile.model.CustomResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
}
