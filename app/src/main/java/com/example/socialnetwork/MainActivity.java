package com.example.socialnetwork;

import android.app.NativeActivity;
import android.os.Bundle;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    UserApiService apiGetUsers;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;

    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        cardView = findViewById(R.id.card_cv);




        apiGetUsers = ApiUtils.getAPIService();

         int results = 30;
         final int pagination = results/3 + 1;

        apiGetUsers.getUsers("picture",results)
                .enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data body =  response.body();
                List<Result> result = body.getResults();


                double spanCount = Math.floor(pagination);
                if(response.isSuccessful()) {
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, (int)spanCount));
                    adapter = new RecycleViewAdapter(MainActivity.this, result);
                    recyclerView.setAdapter(adapter);
                }
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