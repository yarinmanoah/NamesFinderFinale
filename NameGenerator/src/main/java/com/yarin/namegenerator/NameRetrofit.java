package com.yarin.namegenerator;

import android.telecom.Call;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NameRetrofit {
    private static final String BASE_URL = "https://names-finder-finale.vercel.app";
    private static NameRetrofit instance = null;
    private NameAPI NameFinderApi;

    private NameRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NameFinderApi = retrofit.create(NameAPI.class);
    }



    public static synchronized NameRetrofit getInstance() {
        if (instance == null) {
            instance = new NameRetrofit();
        }
        return instance;
    }

    public NameAPI getPetCareApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(NameAPI.class);
    }

}