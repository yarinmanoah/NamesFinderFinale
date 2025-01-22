package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yarin.namegenerator.NameController;
import com.yarin.namesfinder.R;

import java.util.List;

public class ActivityByCategory extends AppCompatActivity {
    private Button BycategoryButtonBack,searchButton;
    private TextView BycategoryLBL ;
    private Spinner letterSpinner;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_category);
        findViews();
        initViews();
        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(adapter);
    }


    private void findViews() {
        BycategoryButtonBack =findViewById(R.id.BycategoryButtonBack);
        BycategoryLBL =findViewById(R.id.BycategoryLBL);
        letterSpinner = findViewById(R.id.letterSpinner);
        searchButton= findViewById(R.id.searchButton);


    }

    private void initViews() {
        BycategoryButtonBack.setOnClickListener(v -> finish());
        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Save the selected category to the global variable
                selectedCategory = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        NameController nameController = new NameController(new MyCallback(ActivityByCategory.this));
        searchButton.setOnClickListener(v -> {
            // Check if a category is selected
            if (selectedCategory != null) {
                // Use the selected category to fetch names
                nameController.fetchByCategory(selectedCategory);
            } else {
                // Handle case where no category is selected
                Toast.makeText(ActivityByCategory.this, "Please select a category", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateNames(List<String> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            stringBuilder.append(name).append("\n");
        }
        BycategoryLBL.setText(stringBuilder.toString());
    }
}