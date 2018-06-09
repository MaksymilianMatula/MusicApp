package com.example.julian.musicapp.topsongs;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.example.julian.musicapp.R;
import com.example.julian.musicapp.api.ApiService;
import com.example.julian.musicapp.api.TrendingList;
import com.google.gson.Gson;

import javax.security.auth.callback.Callback;

import retrofit2.Response;

public class TopSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retrofit2.Call<TrendingList> trendingListCall = ApiService.getService().getTrendingList( "us" ,
                "itunes" , "singles" );
        trendingListCall.enqueue( new retrofit2.Callback<TrendingList>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<TrendingList> call, @NonNull
                    Response<TrendingList> response) {
                TrendingList trendingList = response.body();
                Log.d ( "TAG" , new Gson().toJson(trendingList));
            }
            @Override
            public void onFailure(@NonNull retrofit2.Call<TrendingList> call, Throwable t) {
                Toast.makeText (TopSongsActivity. this , "Blad pobierania danych: " +
                        t.getLocalizedMessage(), Toast. LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
