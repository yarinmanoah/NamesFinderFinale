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

public class ActivityFirstLetterAndCategory extends AppCompatActivity {
    private String selectedLetter;
    private String selectedCategory;
    private Spinner letterSpinner;
    private Spinner categorySpinner;
    private Button a_ByFirstLetterButtonBack;
    private Button a_searchButton;
    private TextView ByFirstLetterAndCategortLBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_letter_and_category);
        findViews();
        initViews();

        // Set up the Letter Spinner
        LetterSpinnerAdapter letterAdapter = new LetterSpinnerAdapter(this, android.R.layout.simple_spinner_item);
        letterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(letterAdapter);
        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Save the selected letter
                selectedLetter = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLetter = null;
            }
        });

        // Set up the Category Spinner
        CategorySpinnerAdapter categoryAdapter = new CategorySpinnerAdapter(this, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Save the selected category
                selectedCategory = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = null;
            }
        });
    }

    private void initViews() {
        a_ByFirstLetterButtonBack.setOnClickListener(v -> finish());
        MyCallback myCallback = new MyCallback(ActivityFirstLetterAndCategory.this);

        // Create an instance of NameController
        NameController nameController = new NameController(myCallback);

        a_searchButton.setOnClickListener(v -> {
            // Check if both letter and category are selected
            if (selectedLetter != null && selectedCategory != null) {
                // Perform your action here, for example:
                nameController.fetchByCategoryAndLetter(selectedCategory,selectedLetter);
                String message = "Selected Letter: " + selectedLetter + ", Selected Category: " + selectedCategory;
                //Toast.makeText(ActivityFirstLetterAndCategory.this, message, Toast.LENGTH_SHORT).show();
            } else {
                // Handle case where not both are selected
                Toast.makeText(ActivityFirstLetterAndCategory.this, "Please select both Letter and Category", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        a_ByFirstLetterButtonBack = findViewById(R.id.a_ByFirstLetterButtonBack);
        letterSpinner = findViewById(R.id.a_letterSpinner);
        categorySpinner = findViewById(R.id.a_categorySpinner);
        a_searchButton = findViewById(R.id.a_searchButton);
        ByFirstLetterAndCategortLBL = findViewById(R.id.ByFirstLetterAndCategortLBL);
    }
    public void updateNames(List<String> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            stringBuilder.append(name).append("\n");
        }
        ByFirstLetterAndCategortLBL.setText(stringBuilder.toString());
    }
}
