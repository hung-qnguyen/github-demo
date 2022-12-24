package deso2.doandinhhoang.appcaphe.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import deso2.doandinhhoang.appcaphe.ItemDetailsActivity;
import deso2.doandinhhoang.appcaphe.R;
import deso2.doandinhhoang.appcaphe.models.Beverage;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.popularViewHolder> {

    public static final String EXTRA_POPULAR = "from_popular";
    private List<Beverage> mBeverages = new ArrayList();
    private Context mContext;

    public PopularAdapter(Context context) {
        this.mContext = context;
    }

    public void setMenu(List<Beverage> beverages) {
        this.mBeverages = beverages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopularAdapter.popularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_product_item_layout,parent,false);
        return new PopularAdapter.popularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.popularViewHolder holder, int position) {
        holder.drinkName.setText(mBeverages.get(position).getDrinkName());
        holder.tvPrice.setText(String.valueOf(mBeverages.get(position).getPrice()));
        Glide.with(mContext)
                .asBitmap()
                .placeholder(R.drawable.drink_placeholder)
                .load(mBeverages.get(position).getImgURL())
                .into(holder.drinkImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                intent.putExtra(EXTRA_POPULAR, mBeverages.get(holder.getAdapterPosition()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mBeverages != null)
            return mBeverages.size();
        else return 0;
    }

    public class popularViewHolder extends RecyclerView.ViewHolder{
        private TextView drinkName, tvPrice;
        private ImageView drinkImage;
//        private Button detailsBtn;
        private CardView cardView;
        public popularViewHolder(@NonNull View itemView) {
            super(itemView);
            drinkName = itemView.findViewById(R.id.popular_drinks_name);
            tvPrice = itemView.findViewById(R.id.popular_price);
            drinkImage = itemView.findViewById(R.id.popular_img);
            cardView = itemView.findViewById(R.id.popular_card);
        }
    }
}
