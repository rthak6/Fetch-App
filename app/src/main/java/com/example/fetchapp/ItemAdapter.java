package com.example.fetchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    // Constructor to initialize the adapter with a list of items
    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false); // Ensure this matches the XML file name
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Bind data to the views
        Item item = itemList.get(position);
        holder.listIdTextView.setText("List ID: " + item.getListId());
        holder.nameTextView.setText("Name: " + item.getName());
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the list
        return itemList.size();
    }

    // ViewHolder class to hold the views for each item
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView listIdTextView;
        TextView nameTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            listIdTextView = itemView.findViewById(R.id.listIdTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}