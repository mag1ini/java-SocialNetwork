package com.example.socialnetwork;

import android.app.NativeActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.socialnetwork.API.ApiUtils;
import com.example.socialnetwork.API.UserApiService;
import com.example.socialnetwork.Models.Data;
import com.example.socialnetwork.Models.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserApiService apiGetUsers;
    private RecyclerView hostsRecyclerView;
    private RecyclerView travellersRecyclerView;
    private RecyclerView bookmarkedRecyclerView;

    private RecycleViewAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        hostsRecyclerView = findViewById(R.id.hosts_recycler);
        travellersRecyclerView = findViewById(R.id.travellers_recycler);
        bookmarkedRecyclerView = findViewById(R.id.bookmarked_recycler);



        apiGetUsers = ApiUtils.getAPIService();

         int results = 30;
         final int pagination = results/3 + 1;

         // Hosts
        apiGetUsers.getUsers("picture",results)
                .enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data body =  response.body();
                List<Result> result = body.getResults();


                double spanCount = Math.floor(pagination);
                if(response.isSuccessful()) {
                    hostsRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, (int)spanCount));
                    adapter = new RecycleViewAdapter(MainActivity.this, result);
                    hostsRecyclerView.setAdapter(adapter);
                }
                int a = 5;
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                int a = 5;
            }
        });

        // Travellers
        apiGetUsers.getUsers("picture",30).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data body = response.body();
                List<Result> result = body.getResults();

                if (response.isSuccessful()) {
                    travellersRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,10));
                    adapter = new RecycleViewAdapter(MainActivity.this, result);
                    travellersRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

        // Bookmarked
        apiGetUsers.getUsers("picture",5).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data body = response.body();
                List<Result> result = body.getResults();
                if (response.isSuccessful()) {
                    bookmarkedRecyclerView.setLayoutManager(new GridLayoutManager( MainActivity.this, 5));
                    adapter = new RecycleViewAdapter(MainActivity.this,result);
                    bookmarkedRecyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });

    }

    private void SetGridLayout() {


    }

}