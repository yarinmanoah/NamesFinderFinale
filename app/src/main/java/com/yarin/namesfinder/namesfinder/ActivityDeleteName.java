package com.yarin.namesfinder.namesfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yarin.namegenerator.NameController;
import com.yarin.namesfinder.R;

public class ActivityDeleteName extends AppCompatActivity {

    private EditText etNameToDelete;  // EditText to enter the name to delete
    private Button btnDelete;         // Button to delete the name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_name); // Make sure you create this layout XML

        // Initialize the UI components
        findViews();
        initViews();
    }

    private void findViews() {
        etNameToDelete = findViewById(R.id.etNameToDelete);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void initViews() {
        // Handle button click to delete the name
        btnDelete.setOnClickListener(v -> {
            String nameToDelete = etNameToDelete.getText().toString().trim();
            if (!nameToDelete.isEmpty()) {
                // Assuming NameController has a method to delete a name
                NameController nameController = new NameController(new MyCallback(ActivityDeleteName.this));
                nameController.deleteName(nameToDelete);  // Pass the name to delete to the controller

                // Show success message
                Toast.makeText(ActivityDeleteName.this, "Name deleted successfully!", Toast.LENGTH_SHORT).show();
                finish();  // Close the activity after deleting the name
            } else {
                Toast.makeText(ActivityDeleteName.this, "Please enter a name to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
