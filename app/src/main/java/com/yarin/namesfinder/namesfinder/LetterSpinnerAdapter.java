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

public class LetterSpinnerAdapter extends ArrayAdapter<String> {

    private final List<String> letters;

    public LetterSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);

        // Initialize the letters list
        letters = new ArrayList<>();
        populateLetters();

        // Set the adapter with the list of letters
        addAll(letters);
    }

    /**
     * Populates the letters list with the English alphabet (A to Z).
     */
    private void populateLetters() {
        for (char c = 'A'; c <= 'Z'; c++) {
            letters.add(String.valueOf(c));
        }
    }

    @Override
    public int getCount() {
        return letters.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return letters.get(position);
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
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(letters.get(position));
        textView.setTextSize(16); // Customize text size
        textView.setPadding(8, 8, 8, 8); // Add padding for better appearance

        return convertView;
    }

    public int findPositionIgnoreCase(String letter) {
        for (int i = 0; i < letters.size(); i++) {
            if (letters.get(i).equalsIgnoreCase(letter)) {
                return i;
            }
        }
        return -1; // Not found
    }
}
