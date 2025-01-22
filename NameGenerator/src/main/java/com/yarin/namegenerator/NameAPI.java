package com.yarin.namegenerator;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.DELETE;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Body;

public interface NameAPI {

        // 1. List All Names
        @GET("list_all_names")
        Call<List<Name>> listAllNames();

        // 2. List by Letter
        @GET("list_by_letter/{char}")
        Call<List<Name>> listByLetter(@Path("char") String letter);

        // 3. List by Category
        @GET("list_by_category/{category}")
        Call<List<Name>> listByCategory(@Path("category") String category);

        // 4. List by Letter and Category
        @GET("list_by_letter_and_category")
        Call<List<Name>> listByLetterAndCategory(
                @Query("letter") String letter,
                @Query("category") String category
        );

        // 5. List Random Name
        @GET("list_random_name")
        Call<Name> listRandomName();

        // 6. Add Name
        @POST("add_name")
        Call<Void> addName(@Body Name newName);

        // 7. Delete Name
        @DELETE("delete_name/{id}")
        Call<Void> deleteName(@Path("id") String id);
}



//package com.yarin.namegenerator;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.http.GET;
//
//public interface NameAPI {
//
//        @GET("raw/KRc8xhkF")
//         Call<List<Name>> loadName();
//
//        @GET("raw/KRc8xhkF")
//        Call<List<Name>> loadNameByLetter();
//
//        @GET("raw/KRc8xhkF")
//        Call<List<Name>> loadNameByCategory();
//        @GET("raw/KRc8xhkF")
//        Call<List<Name>> loadRandomName();
//
//        @GET("raw/KRc8xhkF")
//        Call<List<Name>> loadNameByLetterAndCategory();
//
//    }
//
