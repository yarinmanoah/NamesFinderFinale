package com.yarin.namesfinder.namesfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CategorySpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> categories;

    public CategorySpinnerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.categories = new ArrayList<>();
        // Add the categories
        categories.add("Biblical");
        categories.add("Modern");
        categories.add("Special Nature");

    }

    @Override
    public int getCount() {
        return categories.size();
    }


    @Override
    public String getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(categories.get(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(categories.get(position));

        return convertView;
    }
}