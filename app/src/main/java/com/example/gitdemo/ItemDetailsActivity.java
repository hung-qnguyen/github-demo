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
import com.example.gitdemo.models.Cart;
import com.example.gitdemo.models.Beverage;
import com.example.gitdemo.utils.Utils;

public class ItemDetailsActivity extends AppCompatActivity {
    private static final String TAG_CHECK_QUANT = "quantity_button";
    private static final String TAG_CHECK_CART = "check_cart";

    private TextView drinkName, tvPrice, tvQuantity;
    private ImageView drinkImage, btnAdd, btnMinus;
    private Button btnAddCart, btnCheckout, btnReturn;


    private Cart cartItem;
    private Beverage beverageDetails;
    private String priceText;
    private int quantity = 1;
    private int price;
    private int itemTotal;
    private boolean existInCart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initViews();
        Intent intent = getIntent();
        if (getIntent().hasExtra(EXTRA_DETAILS)) {
            beverageDetails = intent.getParcelableExtra(EXTRA_DETAILS);
            Utils.getInstance().handleAlreadyAdded(beverageDetails);
            setDetailsData(beverageDetails);
            UpdatePrice(quantity);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                UpdatePrice(quantity);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 1) {
                    quantity--;
                    UpdatePrice(quantity);
                } else {
                    Toast.makeText(ItemDetailsActivity.this, "Can't decrease anymore"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (Utils.getInstance().handleAlreadyAdded(beverageDetails)) {
            btnAddCart.setEnabled(false);
        } else {
            btnAddCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartItem = new Cart(beverageDetails, quantity, itemTotal);
                    Utils.getInstance().addToCart(cartItem);
                    Log.d(TAG_CHECK_CART, "Item name: " + cartItem.getCartItem().getDrinkName()
                            + "\nItem quantity: " + cartItem.getQuantity()
                            + "\nItem Total: " + cartItem.getItemTotal()
                            + "\nPosition: " + Utils.getInstance().getCartList().indexOf(cartItem));
                    Toast.makeText(ItemDetailsActivity.this
                            , "Items Added to Cart", Toast.LENGTH_SHORT).show();
                    btnAddCart.setEnabled(false);
                    btnCheckout.setVisibility(View.VISIBLE);
                }
            });

        }
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailsToCart = new Intent(ItemDetailsActivity.this, CartActivity.class);
                startActivity(detailsToCart);
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

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
        btnAddCart = findViewById(R.id.add_cart_btn);
        btnCheckout = findViewById(R.id.details_btn_checkout);
        btnReturn = findViewById(R.id.btn_return);
    }

    private void setDetailsData(Beverage beverage) {
        drinkName.setText(beverage.getDrinkName());
        price = beverage.getPrice();
        tvPrice.setText(priceText);
        Glide.with(this)
                .asBitmap()
                .placeholder(R.drawable.drink_placeholder)
                .load(beverage.getImgURL())
                .into(drinkImage);
    }

    private void UpdatePrice(int quantity) {
        itemTotal = quantity * price;        
        Log.d(TAG_CHECK_QUANT, "Subtotal is" + itemTotal);
        tvQuantity.setText(String.valueOf(quantity));
        priceText = String.valueOf(itemTotal) + " đồng";
        tvPrice.setText(priceText);
    }
}