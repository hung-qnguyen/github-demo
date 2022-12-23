package com.example.gitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.gitdemo.adapters.MenuAdapter;
import com.example.gitdemo.models.Beverage;
import com.example.gitdemo.utils.BeverageViewModel;
import com.example.gitdemo.utils.Utils;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private List<Beverage> mBeverages = new ArrayList<>();
    private BeverageViewModel beverageViewModel;
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

//        mMenuAdapter.setMenu(Utils.getInstance().getAllDrinks());

        beverageViewModel = ViewModelProviders.of(this).get(BeverageViewModel.class);
        beverageViewModel.getAllBeverages().observe(this, new Observer<List<Beverage>>() {
            @Override
            public void onChanged(@Nullable List<Beverage> beverages) {
                mMenuAdapter.setMenu(beverages);
            }
        });

    }




}