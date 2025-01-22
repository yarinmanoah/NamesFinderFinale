package com.yarin.namesfinder.namesfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yarin.namegenerator.NameController;
import com.yarin.namesfinder.R;

public class ActivityAddName extends AppCompatActivity {

    private EditText etName; // EditText to enter the name
    private Button btnAdd;   // Button to submit the new name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name); // Make sure you create this layout XML

        // Initialize the UI components
        findViews();
        initViews();
    }

    private void findViews() {
        etName = findViewById(R.id.etName);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void initViews() {
        // Handle button click to add the name
        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                // Assuming NameController has a method to add a new name
                NameController nameController = new NameController(new MyCallback(ActivityAddName.this));
                nameController.addName(name);  // Pass the new name to the controller for processing

                // Show success message
                Toast.makeText(ActivityAddName.this, "Name added successfully!", Toast.LENGTH_SHORT).show();
                finish();  // Close the activity after adding the name
            } else {
                Toast.makeText(ActivityAddName.this, "Please enter a name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
