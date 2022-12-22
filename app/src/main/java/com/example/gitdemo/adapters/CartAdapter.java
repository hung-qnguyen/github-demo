package com.example.gitdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gitdemo.R;
import com.example.gitdemo.models.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> mCartList = new ArrayList<>();
    private Context mContext;

    public CartAdapter(Context context) {
        this.mContext = context;
    }

    public void setCartList(List<Cart> CartList) {
        this.mCartList = CartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.drinkName.setText(mCartList.get(position).getMenuItem().getDrinkName());
        holder.tvPrice.setText(String.valueOf(mCartList.get(position).getMenuItem().getPrice()));
        Glide.with(mContext)
                .asBitmap()
                .placeholder(R.drawable.drink_placeholder)
                .load(mCartList.get(position).getMenuItem().getImgURL())
                .into(holder.drinkImage);
        holder.tvItemQuant.setText(String.valueOf(mCartList.get(position).getQuantity()));
        float itemTotal = mCartList.get(position).getMenuItem().getPrice() * mCartList.get(position).getQuantity();
        holder.tvItemTotal.setText(String.valueOf(itemTotal));
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCartList.remove(mCartList.get(holder.getAdapterPosition()));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView drinkName, tvPrice, tvItemQuant, tvItemTotal;
        private ImageView drinkImage;
        private ImageButton removeBtn;

        //        private CardView cardView;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkName = itemView.findViewById(R.id.cart_drink_name);
            tvPrice = itemView.findViewById(R.id.cart_item_price);
            tvItemQuant = itemView.findViewById(R.id.cart_item_quant);
            tvItemTotal = itemView.findViewById(R.id.sub_total);
            drinkImage = itemView.findViewById(R.id.cart_drink_img);
            removeBtn = itemView.findViewById(R.id.cart_rm_btn);
//            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
