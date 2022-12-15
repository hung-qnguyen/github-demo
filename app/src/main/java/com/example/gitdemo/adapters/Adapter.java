package com.example.gitdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitdemo.R;
import com.example.gitdemo.models.Menu;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {
    private ArrayList<Menu> mMenu = new ArrayList();

    public Adapter(ArrayList<Menu> menu) {

        this.mMenu = menu;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.textView.setText(mMenu.get(position).getTest_title());
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_test);
            imageView = itemView.findViewById(R.id.test_image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
