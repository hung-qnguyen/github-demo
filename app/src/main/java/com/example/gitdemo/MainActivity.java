package com.example.gitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gitdemo.adapters.MenuAdapter;
import com.example.gitdemo.models.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        AddDummyData(mMenu);
        mMenuAdapter.setMenu(mMenu);
        

    }

    public void AddDummyData(List<Menu> menu){
        for (int i = 0; i<20; i++){
            menu.add(new Menu("Drink Title #"+i,"https://cdn3.iconfinder.com/data/icons/watercolorcafe/512/Latte.png"
                    , 4000*i));
        }
    }


}