package com.example.socialnetwork.API;

import com.example.socialnetwork.Models.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {
    @GET("/api")
    Call<Data> getUsers(@Query("inc") String inc,
                        @Query("results") int results);
}
