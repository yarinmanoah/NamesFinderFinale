package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.yarin.namegenerator.Name;
import com.yarin.namegenerator.NameAPI;
import com.yarin.namegenerator.NameRetrofit;
import com.yarin.namesfinder.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAllNames extends AppCompatActivity {
    private Button allNamesButtonBack;
    private RecyclerView recyclerViewNames;
    private NamesAdapter namesAdapter;
    private NameAPI nameAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_names);

        // Initialize views
        findViews();

        fetchAllNames();

        initViews();
    }

    private void findViews() {
        allNamesButtonBack = findViewById(R.id.allNamesButtonBack);
        recyclerViewNames = findViewById(R.id.recyclerViewNames);

        // Set up RecyclerView
        recyclerViewNames.setLayoutManager(new LinearLayoutManager(this));
        namesAdapter = new NamesAdapter();
        recyclerViewNames.setAdapter(namesAdapter);
    }

    private void initViews() {
        allNamesButtonBack.setOnClickListener(v -> finish());
    }

    private void fetchAllNames() {
        nameAPI = NameRetrofit.getInstance().getNameApi();
        nameAPI.listAllNames().enqueue(new Callback<List<Name>>() {
            @Override
            public void onResponse(Call<List<Name>> call, Response<List<Name>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Name> names = response.body();
                    namesAdapter.setNames(names);
                    Toast.makeText(ActivityAllNames.this, "Names fetched successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityAllNames.this, "Failed to fetch names!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Name>> call, Throwable t) {
                Toast.makeText(ActivityAllNames.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("FetchAllActivity", "Error fetching names: " + t.getMessage());
            }

            });
    }

}
