package com.yarin.namesfinder.namesfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yarin.namegenerator.Name;
import com.yarin.namegenerator.NameAPI;
import com.yarin.namegenerator.NameRetrofit;
import com.yarin.namesfinder.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivityAddName extends AppCompatActivity {

    private EditText etName, etFirstLetter, etCategory; // EditText to enter name, first letter, and category
    private Button btnAdd,  btnAddBack; // Button to submit the new name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name); // Ensure this layout XML exists

        findViews();

        btnAdd.setOnClickListener(v -> addName());
        btnAddBack.setOnClickListener(this::goBackToHome);

    }

    private void findViews() {
        // Bind the UI components to the variables
        etName = findViewById(R.id.etName);
        etFirstLetter = findViewById(R.id.etFirstLetter);
        etCategory = findViewById(R.id.etCategory);
        btnAdd = findViewById(R.id.btnAdd);
        btnAddBack = findViewById(R.id.btnAddBack);
    }

    public void goBackToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void addName(){
        String FirstChar = etFirstLetter.getText().toString();
        String content = etName.getText().toString();
        String category = etCategory.getText().toString();

            if (content.isEmpty() || FirstChar.isEmpty() || category.isEmpty()) {
                Toast.makeText(ActivityAddName.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
             }

            Name name = new Name(FirstChar,content,category);

            NameAPI nameAPI = NameRetrofit.getInstance().getNameApi();

        // Make the API call to add the name
        nameAPI.addName(name).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityAddName.this, "Name added successfully!", Toast.LENGTH_SHORT).show();
                    Log.d("ActivityAddName", "Added name: " + name.toString());
                    finish(); // Close the activity
                } else {
                    Toast.makeText(ActivityAddName.this, "Failed to add name!", Toast.LENGTH_SHORT).show();
                    Log.e("ActivityAddName", "API Response Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ActivityAddName.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ActivityAddName", "API Call Failure: " + t.getMessage());
            }
        });
    }
}
