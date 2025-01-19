package com.yarin.namesfinder.namesfinder;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class LetterSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> letters = new ArrayList<>();

    public LetterSpinnerAdapter(Context context, int resource) {
        super(context, resource);
        // Populate the letters from A to Z
        for (char c = 'A'; c <= 'Z'; c++) {
            letters.add(String.valueOf(c));
        }
        // Set the adapter with the list of letters
        this.addAll(letters);
    }
}