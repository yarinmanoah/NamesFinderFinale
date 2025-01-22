package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yarin.namegenerator.Name;
import com.yarin.namegenerator.NameAPI;
import com.yarin.namegenerator.NameRetrofit;
import com.yarin.namesfinder.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityFirstLetterAndCategory extends AppCompatActivity {
    private String selectedLetter;
    private String selectedCategory;
    private Spinner letterSpinner;
    private Spinner categorySpinner;
    private Button a_ByFirstLetterButtonBack;
    private Button a_searchButton;
    private TextView ByFirstLetterAndCategoryLBL;
    private NameAPI nameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_letter_and_category);

        // Initialize views and variables
        findViews();
        initViews();

        // Set up the Letter Spinner
        LetterSpinnerAdapter letterAdapter = new LetterSpinnerAdapter(this, android.R.layout.simple_spinner_item);
        letterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(letterAdapter);
        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                selectedCategory = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = null;
            }
        });

        // Initialize the API
        nameAPI = NameRetrofit.getInstance().getNameApi();
    }

    private void initViews() {
        // Back button click listener
        a_ByFirstLetterButtonBack.setOnClickListener(v -> finish());

        // Search button click listener
        a_searchButton.setOnClickListener(v -> {
            if (selectedLetter != null && selectedCategory != null) {
                fetchNamesByCategoryAndLetter(selectedCategory, selectedLetter);
            } else {
                Toast.makeText(ActivityFirstLetterAndCategory.this, "Please select both Letter and Category", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        a_ByFirstLetterButtonBack = findViewById(R.id.a_ByFirstLetterButtonBack);
        letterSpinner = findViewById(R.id.a_letterSpinner);
        categorySpinner = findViewById(R.id.a_categorySpinner);
        a_searchButton = findViewById(R.id.a_searchButton);
        ByFirstLetterAndCategoryLBL = findViewById(R.id.ByFirstLetterAndCategortLBL);
    }

    private void fetchNamesByCategoryAndLetter(String category, String letter) {
        nameAPI.listByLetterAndCategory(category, letter).enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateNames(response.body());
                } else {
                    Toast.makeText(ActivityFirstLetterAndCategory.this, "No names found for the selected filters.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                Toast.makeText(ActivityFirstLetterAndCategory.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateNames(List<Name> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Name name : names) {
            stringBuilder.append(name.getContent()).append("\n");
        }
        ByFirstLetterAndCategoryLBL.setText(stringBuilder.toString());
    }
}
