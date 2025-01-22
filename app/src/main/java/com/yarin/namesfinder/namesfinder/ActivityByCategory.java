package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ActivityByCategory extends AppCompatActivity {
    private Button BycategoryButtonBack, searchButton;
    private TextView ByCatgoryLBL;
    private Spinner letterSpinner;
    private String selectedCategory;
    private NamesAdapter namesAdapter;
    private NameAPI nameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_category);

        // Initialize views
        findViews();

        // Initialize NameAPI
        nameAPI = NameRetrofit.getInstance().getNameApi();

        // Initialize the category spinner
        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(adapter);

        // Initialize the button click listeners
        initViews();
    }

    private void findViews() {
        BycategoryButtonBack = findViewById(R.id.BycategoryButtonBack);
        letterSpinner = findViewById(R.id.letterSpinner);
        searchButton = findViewById(R.id.searchButton);
        ByCatgoryLBL = findViewById(R.id.ByCatgoryLBL);
    }

    private void initViews() {
        BycategoryButtonBack.setOnClickListener(v -> finish());

        // Spinner item selection listener
        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String) parent.getItemAtPosition(position); // Save the selected category
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Search button listener to fetch names by category
        searchButton.setOnClickListener(v -> {
            if (selectedCategory != null) {
                fetchNamesByCategory(selectedCategory);
            } else {
                Toast.makeText(ActivityByCategory.this, "Please select a category", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchNamesByCategory(String category) {
        // Fetch names by selected category
        nameAPI.listByCategory(category).enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateNames(response.body());
                    Toast.makeText(ActivityByCategory.this, "Names fetched successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityByCategory.this, "Failed to fetch names!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                Toast.makeText(ActivityByCategory.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateNames(List<Name> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Name name : names) {
            stringBuilder.append(name.getContent()).append("\n"); // Get the content of the Name object
        }
        ByCatgoryLBL.setText(stringBuilder.toString());
    }
}
