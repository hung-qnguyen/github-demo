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
import com.example.gitdemo.models.Menu;
import com.example.gitdemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {
    private static final String TAG_CHECK_QUANT = "quantity_button";
    private static final String TAG_CHECK_CART = "check_cart";
    public static final String EXTRA_DETAILS_TO_CART = "EXTRA_DETAILS_TO_CART";

    private TextView drinkName, tvPrice, tvQuantity;
    private ImageView drinkImage, btnAdd, btnMinus;
    private Button btnAddCart, btnCheckout, btnReturn;


    private Cart cartItem;
//    private List<Cart> mCartList = new ArrayList<>();
    private Menu menuDetails;
    private String priceText;
    private int quantity = 1;
    private float price;
    private float itemSubTotal;
    private boolean existInCart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        initViews();
        Intent intent = getIntent();
        if (getIntent().hasExtra(EXTRA_DETAILS)) {
            menuDetails = intent.getParcelableExtra(EXTRA_DETAILS);
            Utils.getInstance().handleAlreadyAdded(menuDetails);
            setDetailsData(menuDetails);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity++;
                UpdateTvPrice();
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > 1) {
                    quantity--;
                    UpdateTvPrice();
                } else {
                    Toast.makeText(ItemDetailsActivity.this, "Can't decrease anymore"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (Utils.getInstance().handleAlreadyAdded(menuDetails)) {
            btnAddCart.setEnabled(false);
        } else {
            btnAddCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartItem.setQuantity(quantity);
                    cartItem.setItemTotal(itemSubTotal);
                    Utils.getInstance().addToCart(cartItem);
//                    mCartList.add(cartItem);
//                    Utils.getInstance().setCartList(mCartList);
                    Log.d(TAG_CHECK_CART, "Item name: " + cartItem.getMenuItem().getDrinkName()
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
//                Toast.makeText(ItemDetailsActivity.this, "Checkout Button Clicked!", Toast.LENGTH_SHORT).show();
                Intent detailsToCart = new Intent(ItemDetailsActivity.this, CartActivity.class);
//                detailsToCart.putExtra(EXTRA_DETAILS_TO_CART, cartItem);
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
        btnCheckout = findViewById(R.id.btn_checkout);
        btnReturn = findViewById(R.id.btn_return);
    }

    private void setDetailsData(Menu menu) {
        drinkName.setText(menu.getDrinkName());
        price = menu.getPrice();
        priceText = String.valueOf(price) + " đồng";
        tvPrice.setText(priceText);
        Glide.with(this)
                .asBitmap()
                .placeholder(R.drawable.drink_placeholder)
                .load(menu.getImgURL())
                .into(drinkImage);
        cartItem = new Cart(menu, 0, 0);
    }

    private void UpdateTvPrice() {
        itemSubTotal = quantity * price;
        Log.d(TAG_CHECK_QUANT, "Subtotal is" + itemSubTotal);
        tvQuantity.setText(String.valueOf(quantity));
        priceText = String.valueOf(itemSubTotal) + " đồng";
        tvPrice.setText(priceText);
    }

//    private void handleAlreadyAdded(final Menu drinks) {
//        List<Cart> alreadyAddedToCart = Utils.getInstance().getCartList();
//        for (Cart c : alreadyAddedToCart) {
//            if (c.getMenuItem().getDrinkName().equals(drinks.getDrinkName())) {
//                existInCart = true;
//                break;
//            }
//        }
//    }
}