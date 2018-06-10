package com.example.julian.musicapp.topsongs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.julian.musicapp.api.ApiService;
import com.example.julian.musicapp.api.TrendingList;
import com.example.julian.musicapp.api.TrendingSingle;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.user.myapplication.R;

public class TopSongsActivity extends AppCompatActivity {

    RecyclerView rvList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);
        rvList = findViewById(R.id.rvList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Call<TrendingList> trendingListCall = ApiService.getService().getTrendingList("us", "itunes", "singles");
        trendingListCall.enqueue(new Callback<TrendingList>() {
            @Override
            public void onResponse(@NonNull Call<TrendingList> call, @NonNull Response<TrendingList> response) {
                TrendingList trendingList = response.body();

//                Log.d("TAG", new Gson().toJson(trendingList));
                if (trendingList != null && trendingList.trending != null){
                    setList(trendingList.trending);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrendingList> call, Throwable t) {
                Toast.makeText(TopSongsActivity.this, "Blad pobierania danych: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setList(List<TrendingSingle> singles) {
        TopSongsAdapter topSongsAdapter = new TopSongsAdapter(singles);
        rvList.setAdapter(topSongsAdapter);

        LinearLayoutManager lineralLayoutManager = new LinearLayoutManager(this);
        lineralLayoutManager.setReverseLayout(true);
        lineralLayoutManager.setStackFromEnd(true);
        rvList.setLayoutManager(lineralLayoutManager);

        rvList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();

        return true;
    }


}