package com.example.chatapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdepter extends RecyclerView.Adapter<UserAdepter.viewholder> {

    @NonNull
    @Override
    public UserAdepter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdepter.viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {

        public viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
