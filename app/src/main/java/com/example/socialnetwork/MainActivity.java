package com.example.socialnetwork;

import android.app.NativeActivity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.GridView;
import android.widget.HorizontalScrollView;

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
    private HorizontalScrollView hosts_hs;
    private RecyclerView hostsRecyclerView;
    private RecyclerView travellersRecyclerView;
    private RecyclerView bookmarkedRecyclerView;

    private RecycleViewAdapter adapter;

    final int MAX_ROWS = 3;
    final int MAX_COLUMNS_IN_SCREEN = 5; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        hostsRecyclerView = findViewById(R.id.hosts_recycler);
        travellersRecyclerView = findViewById(R.id.travellers_recycler);
        bookmarkedRecyclerView = findViewById(R.id.bookmarked_recycler);
        hosts_hs = findViewById(R.id.hosts_hs);
        apiGetUsers = ApiUtils.getAPIService();

         // Hosts
        final int results1 = 9;
        apiGetUsers.getUsers("picture",results1).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful()) {
                    setUsers(response, hostsRecyclerView);
                }
                else{
                    Log.println(Log.ERROR,"error","error hosts");
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                boolean isFail = true;
            }
        });

        // Travellers
        final int results2 = 49;
        apiGetUsers.getUsers("picture",results2).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful()) {
                    setUsers(response, travellersRecyclerView);
                }
                else{
                    Log.println(Log.ERROR,"error","error travellers");
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                boolean isFail = true;
            }
        });

        // Bookmarked
        final int results3 = 3;
        apiGetUsers.getUsers("picture",results3).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if(response.isSuccessful()) {
                    setUsers(response, bookmarkedRecyclerView);
                }
                else {
                    Log.println(Log.ERROR,"error","error bookmarked");
                }
            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                boolean isFail = true;
            }
        });

    }

    private void setUsers(Response<Data> response, RecyclerView recycler) {
        Data body = response.body();

        List<Result> result = body.getResults();
        int amountResults = body.getResults().size();

        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, getAmountCols(amountResults)));
        adapter = new RecycleViewAdapter(MainActivity.this, result);
        recycler.setAdapter(adapter);
    }

    private int  getAmountCols(int amountResults) {
        int spanCols = (int) Math.ceil(amountResults/ (double) MAX_ROWS);
        return (spanCols < MAX_COLUMNS_IN_SCREEN)?
                MAX_COLUMNS_IN_SCREEN : spanCols;

    }



}