package com.yarin.namesfinder.namesfinder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yarin.namegenerator.NameAPI;
import com.yarin.namegenerator.NameRetrofit;
import com.yarin.namesfinder.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDeleteName extends AppCompatActivity {

    private EditText etIdToDelete;  // EditText to enter the ID to delete
    private Button btnDelete;      // Button to delete the name
    private NameAPI nameAPI;       // Retrofit API interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_name); // Ensure this layout exists

        // Initialize Retrofit API
        nameAPI = NameRetrofit.getInstance().getNameApi();

        // Initialize the UI components
        findViews();
        initViews();
    }

    private void findViews() {
        etIdToDelete = findViewById(R.id.etIdToDelete);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void initViews() {
        // Handle button click to delete the name by ID
        btnDelete.setOnClickListener(v -> {
            String idToDelete = etIdToDelete.getText().toString().trim();
            if (!idToDelete.isEmpty()) {
                deleteNameById(idToDelete); // Call the delete method
            } else {
                Toast.makeText(ActivityDeleteName.this, "Please enter an ID to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteNameById(String id) {
        // Call the deleteName API method
        nameAPI.deleteName(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityDeleteName.this, "Name deleted successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after deletion
                } else {
                    Toast.makeText(ActivityDeleteName.this, "Failed to delete name. Please try again.", Toast.LENGTH_SHORT).show();
                    Log.e("DeleteName", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ActivityDeleteName.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("DeleteName", "Error deleting name: " + t.getMessage());
            }
        });
    }
}
