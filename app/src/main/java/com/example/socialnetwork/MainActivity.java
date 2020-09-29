package com.example.socialnetwork;

import android.os.Bundle;

import com.example.socialnetwork.API.ApiUtils;
import com.example.socialnetwork.API.UserApiService;
import com.example.socialnetwork.Models.Data;
import com.example.socialnetwork.Models.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserApiService apiGetUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        apiGetUsers = ApiUtils.getAPIService();


        apiGetUsers.getUsers("picture",5)
                .enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data body =  response.body();
                List<Result> results = body.getResults();
                int a = 5;
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                int a = 5;
            }
        });

    }

    private void SetGridLayout() {


    }

}