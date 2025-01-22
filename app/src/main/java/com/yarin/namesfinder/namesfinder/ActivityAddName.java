package com.yarin.namesfinder.namesfinder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yarin.namegenerator.Name;
import com.yarin.namegenerator.NameAPI;
import com.yarin.namegenerator.NameRetrofit;
import com.yarin.namesfinder.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityAddName extends AppCompatActivity {

    private EditText etName, etFirstLetter, etCategory; // EditText to enter name, first letter, and category
    private Button btnAdd; // Button to submit the new name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name); // Ensure this layout XML exists

        findViews();

        btnAdd.setOnClickListener(v -> addName());

    }

    private void findViews() {
        // Bind the UI components to the variables
        etName = findViewById(R.id.etName);
        etFirstLetter = findViewById(R.id.etFirstLetter);
        etCategory = findViewById(R.id.etCategory);
        btnAdd = findViewById(R.id.btnAdd);
    }


    private void addName(){
            String content = etName.getText().toString();
            String firstLetter = etFirstLetter.getText().toString();
            String category = etCategory.getText().toString();

            if (content.isEmpty() && firstLetter.isEmpty() && category.isEmpty()) {
                Toast.makeText(ActivityAddName.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
             }

            Name name = new Name(firstLetter,category,content);

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
