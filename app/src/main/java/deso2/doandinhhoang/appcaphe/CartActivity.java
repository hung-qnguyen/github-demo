package deso2.doandinhhoang.appcaphe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import deso2.doandinhhoang.appcaphe.fragment.CartFragment;

public class CartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.checkout_placeholder, new CartFragment()).commit();
    }

}