package com.example.gitdemo;

import static com.example.gitdemo.ItemDetailsActivity.EXTRA_DETAILS_TO_CART;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.gitdemo.adapters.CartAdapter;
import com.example.gitdemo.adapters.MenuAdapter;
import com.example.gitdemo.models.Cart;
import com.example.gitdemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
//    private List<Cart> mCartList = new ArrayList<>();
    private RecyclerView mCartRV;
    private CartAdapter mCartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
//        mCartList.add(intent.getParcelableExtra(EXTRA_DETAILS_TO_CART));
        mCartRV = findViewById(R.id.cart_rv);
        mCartAdapter = new CartAdapter(this);
        mCartRV.setAdapter(mCartAdapter);
        mCartRV.setLayoutManager(new LinearLayoutManager(this));
        mCartAdapter.setCartList(Utils.getInstance().getCartList());
    }
}