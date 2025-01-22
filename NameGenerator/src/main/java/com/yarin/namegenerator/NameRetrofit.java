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

//package com.yarin.namegenerator;
//
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class NameController {
//
//    static final String BASE_URL = "https://pastebin.com/";
//    List<String> nameContents ;
//    private CallBack_Name callBackNames;
//    String resolt;
//
//
//    public NameController(CallBack_Name callBackNames) {
//        this.callBackNames = callBackNames;
//    }
//
//    public void fetchAllName() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        NameAPI nameAPI = retrofit.create(NameAPI.class);
//
//        Call<List<Name>> call = nameAPI.loadName();
//        call.enqueue(new Callback<List<Name>>() {
//
//            @Override
//            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
//                if (response.isSuccessful()) {
//                    List<Name> names = response.body();
//                    //List<String> nameContents = new ArrayList<>();
//
//                   // for (Name name : names) {
//                      //  nameContents.add(name.getContent());
//                    callBackNames.successNames(names);
//                        Log.d("sstts",""+resolt);
//
//
//
//                } else {
//                     callBackNames.errorNames("Failed to fetch Names");
//                }
//
//            }
//
//
//            @Override
//            public void onFailure(Call<List<Name>> call, Throwable t) {
//                callBackNames.errorNames(t.getMessage());
//                t.printStackTrace();
//            }
//        });
//
//    }
//
//    public void fetchByLetter(String letter) {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        NameAPI nameAPI = retrofit.create(NameAPI.class);
//
//        Call<List<Name>>  call = nameAPI.loadNameByLetter();
//        call.enqueue(new Callback<List<Name>>() {
//            @Override
//            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<Name> names = response.body();
//
//                    callBackNames.fillterByLetter(letter,names);
//
//                   // Log.d("sapirr",""+names);
//                } else {
//                    callBackNames.errorNames("Failed to fetch Name");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Name>> call, Throwable t) {
//                callBackNames.errorNames(t.getMessage());
//                t.printStackTrace();
//            }
//        });
//    }
//
//    public void fetchByCategory(String category) {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        NameAPI nameAPI = retrofit.create(NameAPI.class);
//
//        Call<List<Name>>  call = nameAPI.loadNameByCategory();
//        call.enqueue(new Callback<List<Name>>() {
//            @Override
//            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<Name> names = response.body();
//                   // Log.d("ssts",""+category);
//                    callBackNames.fillterByCategory(category,names);
//
//
//                } else {
//                    callBackNames.errorNames("Failed to fetch Name");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Name>> call, Throwable t) {
//                callBackNames.errorNames(t.getMessage());
//                t.printStackTrace();
//            }
//        });
//    }
//
//    public void fetchByCategoryAndLetter(String category,String letter) {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        NameAPI nameAPI = retrofit.create(NameAPI.class);
//
//        Call<List<Name>>  call = nameAPI.loadNameByLetterAndCategory();
//        call.enqueue(new Callback<List<Name>>() {
//            @Override
//            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<Name> names = response.body();
//                    // Log.d("ssts",""+category);
//                    callBackNames.fillterByCategoryAndLetter(category,letter,names);
//
//
//                } else {
//                    callBackNames.errorNames("Failed to fetch Name");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Name>> call, Throwable t) {
//                callBackNames.errorNames(t.getMessage());
//                t.printStackTrace();
//            }
//        });
//    }
//    public void fetchByRandom() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        NameAPI nameAPI = retrofit.create(NameAPI.class);
//
//        Call<List<Name>>  call = nameAPI.loadRandomName();
//        call.enqueue(new Callback<List<Name>>() {
//            @Override
//            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<Name> names = response.body();
//                    // Log.d("ssts",""+category);
//                    callBackNames.Randomfillter(names);
//
//
//                } else {
//                    callBackNames.errorNames("Failed to fetch Name");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Name>> call, Throwable t) {
//                callBackNames.errorNames(t.getMessage());
//                t.printStackTrace();
//            }
//        });
//    }
//
//
//    public interface CallBack_Name {
//
//        void successNames(List<Name> names);
//
//        void errorNames(String error);
//         void fillterByLetter (String leterFillter, List<Name> names);
//        void fillterByCategory (String categoryFillter, List<Name> names);
//        void fillterByCategoryAndLetter (String categoryFillter,String letterFilter, List<Name> names);
//        void Randomfillter ( List<Name> names);
//    }
//}
