package com.sujin.ramco;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://192.168.43.16:3000";

    @GET("/order?lat=&lng=")
    Call<String> sendLatLong(@Query("lat") String lat,@Query("lng") String lng);
}