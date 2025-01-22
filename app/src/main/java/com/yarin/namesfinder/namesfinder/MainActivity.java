package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        // Initialize UI components
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
        // Navigate to Activity showing all Names
        btnListNames.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityAllNames.class);
            startActivity(intent);
        });

        // Navigate to Activity showing Names by Letter
        btnListByLetter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityFirstLetter.class);
            startActivity(intent);
        });

        // Navigate to Activity showing Names by Category
        btnListByCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityByCategory.class);
            startActivity(intent);
        });

        // Navigate to Activity showing Names by Category and Letter
        btnLetterCategory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityFirstLetterAndCategory.class);
            startActivity(intent);
        });

        // Navigate to Activity showing Random Names
        btnListByRandom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityRandom.class);
            startActivity(intent);
        });

        // Navigate to Activity to Add a new Name
        btnAddName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityAddName.class);
            startActivity(intent);
        });

        // Navigate to Activity to Delete a Name
        btnDeleteName.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityDeleteName.class);
            startActivity(intent);
        });
    }
}
