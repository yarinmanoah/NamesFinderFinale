package com.yarin.namesfinder.namesfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yarin.namegenerator.Name;
import com.yarin.namesfinder.R;

import java.util.ArrayList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NameViewHolder> {
    private List<Name> names = new ArrayList<>();

    // Constructor to initialize the pet list
    // Empty constructor to initialize with an empty list
    public NamesAdapter() {
        this.names = new ArrayList<>();
    }

    // Constructor to initialize the pet list
    public NamesAdapter(List<Name> names) {
        this.names = names != null ? names : new ArrayList<>(); // Handle null safety
    }

    public void updateNames(List<Name> newNames) {
        this.names.clear();
        this.names.addAll(newNames);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        Name name = names.get(position);
        holder.nameTextView.setText(name.getContent());

    }

    @Override
    public int getItemCount() {
        return names != null ? names.size() : 0;
    }

    public void setNames(List<Name> names) {
        this.names = names;
        notifyDataSetChanged();
    }

    static class NameViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameItemTextView);
        }
    }
}
