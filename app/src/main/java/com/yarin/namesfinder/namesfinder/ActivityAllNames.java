package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yarin.namegenerator.NameController;
import com.yarin.namesfinder.R;

import java.util.List;

public class ActivityAllNames extends AppCompatActivity {
    private Button allNamesButtonBack;
    private TextView allNamesLBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_names);
        findViews();
        initViews();

        // Create a callback and initialize the NameController
        MyCallback myCallback = new MyCallback(this); // Pass reference to this activity
        NameController nameController = new NameController(myCallback);
        nameController.fetchAllName(); // Fetch all names from the API
    }

    // Method to update the UI with the fetched names
    public void updateNames(List<String> names) {
        // Ensure the UI is updated on the main thread
        runOnUiThread(() -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (String name : names) {
                stringBuilder.append(name).append("\n");
            }
            allNamesLBL.setText(stringBuilder.toString());
        });
    }

    // Initialize the views
    private void findViews() {
        allNamesButtonBack = findViewById(R.id.allNamesButtonBack);
        allNamesLBL = findViewById(R.id.allNamesLBL);
    }

    // Set up the button's onClickListener
    private void initViews() {
        allNamesButtonBack.setOnClickListener(v -> finish()); // Finish the activity when the back button is clicked
    }
}


//package com.yarin.namesfinder.namesfinder;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.yarin.namegenerator.NameController;
//import com.yarin.namesfinder.R;
//
//import java.util.List;
//
//public class ActivityAllNames extends AppCompatActivity {
//    private Button allNamesButtonBack;
//    private TextView allNamesLBL ;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_names);
//        findViews();
//        initViews();
//
//        MyCallback myCallback = new MyCallback(this); // Pass reference to this activity
//        NameController nameController = new NameController(myCallback);
//        nameController.fetchAllName();
//
//
//    }
//    public void updateNames(List<String> names) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String name : names) {
//            stringBuilder.append(name).append("\n");
//        }
//        allNamesLBL.setText(stringBuilder.toString());
//    }
//
//
//    private void findViews() {
//        allNamesButtonBack =findViewById(R.id.allNamesButtonBack);
//        allNamesLBL =findViewById(R.id.allNamesLBL);
//
//
//    }
//
//    private void initViews() {
//        allNamesButtonBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
//    }
//}