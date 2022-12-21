package com.example.gitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gitdemo.adapters.MenuAdapter;
import com.example.gitdemo.models.Cart;
import com.example.gitdemo.models.Menu;
import com.example.gitdemo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Cart> cartList=new ArrayList<>();
    private List<Menu> mMenu = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.menu_rv);
        mMenuAdapter = new MenuAdapter(this);
        mRecyclerView.setAdapter(mMenuAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mMenuAdapter.setMenu(Utils.getInstance().getAllDrinks());


    }




}