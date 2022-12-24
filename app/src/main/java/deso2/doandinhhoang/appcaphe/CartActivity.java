package deso2.doandinhhoang.appcaphe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import deso2.doandinhhoang.appcaphe.adapters.CartAdapter;
import deso2.doandinhhoang.appcaphe.models.Cart;
import deso2.doandinhhoang.appcaphe.utils.Utils;
import fragment.CartFragment;

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