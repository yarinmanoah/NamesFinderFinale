package com.yarin.namesfinder.namesfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yarin.namegenerator.NameController;
import com.yarin.namesfinder.R;

import java.util.List;

public class ActivityRandom extends AppCompatActivity {
    TextView randomNameLBL;
    Button randomButtonBack;
    String random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        findViews();
        initViews();

        MyCallback myCallback = new MyCallback(ActivityRandom.this);

        // Create an instance of NameController
        NameController nameController = new NameController( myCallback);
       nameController.fetchByRandom();

    }

    private void initViews() {
        randomButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    private void findViews() {
        randomButtonBack =findViewById(R.id.randomButtonBack);
        randomNameLBL = findViewById(R.id.randomNameLBL);

    }
    public void updateNames(List<String> names) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : names) {
            stringBuilder.append(name).append("\n");
        }
        randomNameLBL.setText(stringBuilder.toString());
    }
}