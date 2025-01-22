package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yarin.namegenerator.Name;
import com.yarin.namegenerator.NameAPI;
import com.yarin.namegenerator.NameRetrofit;
import com.yarin.namesfinder.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRandom extends AppCompatActivity {
    private TextView randomNameLBL;
    private Button randomButtonBack;
    private NameAPI nameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        // Initialize views and API
        findViews();
        initViews();
        nameAPI = NameRetrofit.getInstance().getNameApi();

        // Fetch and display a random name
        fetchRandomName();
    }

    private void initViews() {
        randomButtonBack.setOnClickListener(v -> finish());
    }

    private void findViews() {
        randomButtonBack = findViewById(R.id.randomButtonBack);
        randomNameLBL = findViewById(R.id.randomNameLBL);
    }

    private void fetchRandomName() {
        nameAPI.listRandomName().enqueue(new Callback<Name>() {
            @Override
            public void onResponse(Call<Name> call, Response<Name> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Display the random name
                    Name randomName = response.body();
                    randomNameLBL.setText(randomName.getContent());
                } else {
                    // Handle empty or error response
                    Toast.makeText(ActivityRandom.this, "Failed to fetch a random name.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Name> call, Throwable t) {
                // Handle failure
                Toast.makeText(ActivityRandom.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
