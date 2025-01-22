package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yarin.namegenerator.NameController;
import com.yarin.namesfinder.R;

public class MainActivity extends AppCompatActivity {

    private Button btnListNames;
    private Button btnListByLetter;
    private Button btnListByCategory;
    private Button btnLetterCategory;
    private Button btnListByRandom;
    private Button btnAddName;
    private Button btnDeleteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the callback and pass it to the NameController
        MyCallback myCallback = new MyCallback(this);

        // Create an instance of NameController
        NameController nameController = new NameController(myCallback);

        // Fetch random name (just as an example)
        nameController.fetchByRandom(); // You can remove or change this based on UI logic

        // Initialize the UI components
        findViews();
        initViews();
    }

    // Find views by their ID
    private void findViews() {
        btnListNames = findViewById(R.id.btnListNames);
        btnListByLetter = findViewById(R.id.btnListByLetter);
        btnListByCategory = findViewById(R.id.btnListByCategory);
        btnLetterCategory = findViewById(R.id.btnLetterCategory);
        btnListByRandom = findViewById(R.id.btnListByRandom);
        btnAddName = findViewById(R.id.btnAddName);
        btnDeleteName = findViewById(R.id.btnDeleteName);
    }

    // Initialize button click listeners
    private void initViews() {
        // Button to navigate to All Names Activity
        btnListNames.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityAllNames.class);
            startActivity(intent);
        });

        // Button to navigate to Activity with Names by Letter
        btnListByLetter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityFirstLetter.class);
            startActivity(intent);
        });

        // Button to navigate to Activity with Names by Category
        btnListByCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityByCategory.class);
            startActivity(intent);
        });

        // Button to navigate to Activity with Names by Category and Letter
        btnLetterCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityFirstLetterAndCategory.class);
            startActivity(intent);
        });

        // Button to navigate to Activity with Random Names
        btnListByRandom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityRandom.class);
            startActivity(intent);
        });

        // Button to navigate to Activity To Add new Name
        btnAddName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityAddName.class);
            startActivity(intent);        });

        // Button to navigate to Activity To Delete Name
        btnDeleteName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityDeleteName.class);
            startActivity(intent);        });
    }
}


//package com.yarin.namesfinder.namesfinder;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import com.google.gson.Gson;
//import com.yarin.namegenerator.Name;
//import com.yarin.namegenerator.NameController;
//import com.yarin.namesfinder.R;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    private Button btnListNames;
//    private Button btnListByLetter;
//    private Button btnListByCategory;
//    private Button btnLetterCategory;
//    private Button btnListByRandom;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ArrayList<Name> arr =DataManger.getNames();
//        String json = new Gson().toJson(arr);
//        int x=9;
//
//        MyCallback myCallback = new MyCallback(this);
//
//        // Create an instance of NameController
//        NameController nameController = new NameController( myCallback);
//
//        // Call the fetchAllName method to fetch all names
//     //   nameController.fetchAllName();
//
//        // Call the fetchByLetter method to fetch names by a specific letter
//        String letter = "D"; // Example letter
////        nameController.fetchByLetter(letter);
//        String category ="Biblical";
//      //  nameController.fetchByCategory(category);
//        //nameController.fetchByCategoryAndLetter(category,letter);
//        nameController.fetchByRandom();
//
//     findViews();
//     initViews();
//        int y =10;
//    }
//
//    private void findViews() {
//        btnListNames =findViewById(R.id.btnListNames);
//        btnListByLetter =findViewById(R.id.btnListByLetter);
//        btnListByCategory =findViewById(R.id.btnListByCategory);
//        btnLetterCategory =findViewById(R.id.btnLetterCategory);
//        btnListByRandom =findViewById(R.id.btnListByRandom);
//
//    }
//
//    private void initViews() {
//        btnListNames.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this,ActivityAllNames.class);
//            startActivity(intent);
//
//        });
//
//        btnListByLetter.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this,ActivityFirstLetter.class);
//            startActivity(intent);
//
//        });
//
//        btnListByCategory.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this,ActivityByCategory.class);
//            startActivity(intent);
//
//
//        });
//
//        btnLetterCategory.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ActivityFirstLetterAndCategory.class);
//            startActivity(intent);
//
//
//        });
//
//        btnListByRandom.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, ActivityRandom.class);
//            startActivity(intent);
//
//
//        });
//
//
//    }
//
//}