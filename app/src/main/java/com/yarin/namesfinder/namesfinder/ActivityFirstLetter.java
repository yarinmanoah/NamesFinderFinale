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

public class ActivityFirstLetter extends AppCompatActivity {
    private String selectedLetter; // Holds the selected letter
    private Spinner letterSpinner;
    private Button searchButton, ByFirstLetterButtonBack;
    private TextView ByFirstLetterLBL;
    private NameAPI nameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_letter);

        // Initialize views
        findViews();
        initViews();

        // Set up spinner adapter
        LetterSpinnerAdapter adapter = new LetterSpinnerAdapter(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        letterSpinner.setAdapter(adapter);

        // Set the OnItemSelectedListener
        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Save the selected letter to the global variable
                selectedLetter = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Initialize the API
        nameAPI = NameRetrofit.getInstance().getNameApi();

        // Fetch names on button click
        searchButton.setOnClickListener(v -> {
            if (selectedLetter != null && !selectedLetter.isEmpty()) {
                fetchByLetter(selectedLetter);
            } else {
                Toast.makeText(ActivityFirstLetter.this, "Please select a letter", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findViews() {
        ByFirstLetterButtonBack = findViewById(R.id.ByFirstLetterButtonBack);
        letterSpinner = findViewById(R.id.letterSpinner);
        ByFirstLetterLBL = findViewById(R.id.ByFirstLetterLBL);
        searchButton = findViewById(R.id.searchButton);
    }

    private void initViews() {
        ByFirstLetterButtonBack.setOnClickListener(v -> finish());
    }

    private void fetchByLetter(String letter) {
        nameAPI.listByLetter(letter).enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update the names in the TextView
                    updateNames(response.body());
                    Toast.makeText(ActivityFirstLetter.this, "Names fetched successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityFirstLetter.this, "Failed to fetch names!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                Toast.makeText(ActivityFirstLetter.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateNames(List<Name> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Name name : names) {
            stringBuilder.append(name.getContent()).append("\n"); // Get the content of the Name object
        }
        ByFirstLetterLBL.setText(stringBuilder.toString());
    }
}
