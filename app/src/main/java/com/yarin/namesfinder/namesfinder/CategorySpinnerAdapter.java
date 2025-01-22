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
    private final Context context;
    private final List<String> categories;

    public CategorySpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;

        // Initialize the categories list
        this.categories = new ArrayList<>();
        addDefaultCategories();
    }

    /**
     * Add default categories to the list. This method makes it easier to extend or modify categories in the future.
     */
    private void addDefaultCategories() {
        categories.add("Biblical");
        categories.add("Modern");
        categories.add("Special Nature");
        categories.add("royal");
        categories.add("common");
        categories.add("popular");

    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent, android.R.layout.simple_spinner_item);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent, android.R.layout.simple_spinner_dropdown_item);
    }

    /**
     * Helper method to create and configure a spinner item view.
     */
    private View createView(int position, @Nullable View convertView, @NonNull ViewGroup parent, int layoutId) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(categories.get(position));
        textView.setTextSize(16); // Customize text size if needed
        textView.setPadding(8, 8, 8, 8); // Add padding for better appearance

        return convertView;
    }

    /**
     * Finds the position of a category in the spinner, case-insensitively.
     */
    public int findPositionIgnoreCase(String category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equalsIgnoreCase(category)) {
                return i;
            }
        }
        return -1; // Not found
    }
}
