package com.example.gitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.gitdemo.adapters.CartAdapter;
import com.example.gitdemo.models.Cart;
import com.example.gitdemo.utils.Utils;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private static final String TAG_CALC_TOTAL = "calculate_total";
    private float subTotal, total;
    private int deliveryFee = 4000;
//    private List<Cart> mCartList = new ArrayList<>();
    private RecyclerView mCartRV;
    private CartAdapter mCartAdapter;
    private TextView tvSubTotal, tvDeliveryFee, tvTotal;
    private Button btnToMenu, btnCheckout;
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
        initViews();
        calculateTotals();
        tvSubTotal.setText(String.valueOf(subTotal)+Utils.CURRENCY);
        tvDeliveryFee.setText(String.valueOf(deliveryFee)+Utils.CURRENCY);
        tvTotal.setText(String.valueOf(total)+Utils.CURRENCY);
    }

    private void initViews() {
        tvSubTotal = findViewById(R.id.sub_total);
        tvDeliveryFee = findViewById(R.id.delivery_fee);
        tvTotal = findViewById(R.id.total);
        btnToMenu = findViewById(R.id.btn_to_menu);
        btnCheckout = findViewById(R.id.btn_checkout);
    }

    private void calculateTotals (){
        List<Cart> cartList = Utils.getInstance().getCartList();
        for (Cart c : cartList){
            subTotal += c.getItemTotal();
//        Log.d(TAG_CALC_TOTAL,"Item Total: " + c.getItemTotal()
//                + "\nPosition: " + cartList.indexOf(c));
        }
        total = subTotal+deliveryFee;
    }

}