package com.example.gitdemo;

import static com.example.gitdemo.adapters.MenuAdapter.EXTRA_DETAILS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gitdemo.models.Menu;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {
    private static final String TAG = "quantity_button";

    private TextView drinkName, tvPrice, tvQuantity;
    private ImageView drinkImage, btnAdd, btnMinus;
    private Menu menuDetails;

    private String priceText;
    private int quantity = 0;
    private float price;
    public float itemSubTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initViews();
        Intent intent = getIntent();
        if (getIntent().hasExtra(EXTRA_DETAILS)) {
            menuDetails = intent.getParcelableExtra(EXTRA_DETAILS);
            drinkName.setText(menuDetails.getDrinkName());
            price = menuDetails.getPrice();
            priceText = String.valueOf(price)+" đồng";
            tvPrice.setText(priceText);
            Glide.with(this)
                    .asBitmap()
                    .placeholder(R.drawable.drink_placeholder)
                    .load(menuDetails.getImgURL())
                    .into(drinkImage);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                itemSubTotal = quantity * price;
                Log.d(TAG, "Subtotal is" + itemSubTotal);
                tvQuantity.setText(String.valueOf(quantity));
                priceText = String.valueOf(itemSubTotal)+" đồng";
                tvPrice.setText(priceText);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 1) {
                    quantity--;
                    itemSubTotal = quantity * price;
                    Log.d(TAG, "Subtotal is" + itemSubTotal);
                    tvQuantity.setText(String.valueOf(quantity));
                    priceText = String.valueOf(itemSubTotal)+" đồng";
                    tvPrice.setText(priceText);
                } else {
                    Toast.makeText(ItemDetailsActivity.this, "Can't decrease anymore"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initViews() {
        drinkName = findViewById(R.id.details_name);
        tvPrice = findViewById(R.id.details_price);
        tvQuantity = findViewById(R.id.tv_quantity);
        drinkImage = findViewById(R.id.details_img);
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);
    }

//    private void UpdateTvPrice(){
//        priceText = String.valueOf(itemSubTotal)+" đồng";
//        tvPrice.setText(priceText);
//    }
}