package com.yarin.namegenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NameRetrofit {
    private static final String BASE_URL = "https://names-finder-finale.vercel.app/";
    private static NameRetrofit instance = null; // Singleton instance
    private final NameAPI nameAPI; // NameAPI instance

    // Private constructor for singleton
    private NameRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        nameAPI = retrofit.create(NameAPI.class);
    }

    // Singleton instance getter
    public static synchronized NameRetrofit getInstance() {
        if (instance == null) {
            instance = new NameRetrofit();
        }
        return instance;
    }

    // Getter for the NameAPI instance
    public NameAPI getNameApi() {
        return nameAPI;
    }
}

