package deso2.doandinhhoang.appcaphe.adapters;

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

import java.util.ArrayList;
import java.util.List;

import deso2.doandinhhoang.appcaphe.R;
import deso2.doandinhhoang.appcaphe.models.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> mCartList = new ArrayList<>();
    private Context mContext;
    private OnCartListener mOnCartListener;
    public CartAdapter(Context context, OnCartListener onCartListener) {
        this.mContext = context;
        this.mOnCartListener = onCartListener;
    }

    public void setCartList(List<Cart> CartList) {
        this.mCartList = CartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartAdapter.CartViewHolder(view, mOnCartListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.drinkName.setText(mCartList.get(position).getCartItem().getDrinkName());
        holder.tvPrice.setText(String.valueOf(mCartList.get(position).getCartItem().getPrice()));
        Glide.with(mContext)
                .asBitmap()
                .placeholder(R.drawable.drink_placeholder)
                .load(mCartList.get(position).getCartItem().getImgURL())
                .into(holder.drinkImage);
        holder.tvItemQuant.setText(String.valueOf(mCartList.get(position).getQuantity()));
//        float itemTotal = mCartList.get(position).getCartItem().getPrice() * mCartList.get(position).getQuantity();
        holder.tvItemTotal.setText(String.valueOf(mCartList.get(position).getItemTotal()));
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView drinkName, tvPrice, tvItemQuant, tvItemTotal;
        private ImageView drinkImage;
        private ImageButton removeBtn;
        private OnCartListener onCartListener;

        //        private CardView cardView;
        public CartViewHolder(@NonNull View itemView, OnCartListener onCartListener) {
            super(itemView);
            drinkName = itemView.findViewById(R.id.cart_drink_name);
            tvPrice = itemView.findViewById(R.id.cart_item_price);
            tvItemQuant = itemView.findViewById(R.id.cart_item_quant);
            tvItemTotal = itemView.findViewById(R.id.sub_total);
            drinkImage = itemView.findViewById(R.id.cart_drink_img);
            removeBtn = itemView.findViewById(R.id.cart_rm_btn);
            this.onCartListener = onCartListener;
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCartListener.onCartClick(getLayoutPosition());
        }
    }

    public interface OnCartListener {
        void onCartClick(int position);
    }
}
