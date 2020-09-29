package com.example.socialnetwork.API;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://randomuser.me";

    public static UserApiService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(UserApiService.class);
    }

}