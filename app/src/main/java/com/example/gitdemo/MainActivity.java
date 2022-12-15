package com.example.gitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gitdemo.adapters.Adapter;
import com.example.gitdemo.models.Menu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Menu> mMenu = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.test_recyclerView);

        mAdapter = new Adapter(mMenu);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddDummyData(mMenu);
       

    }

    public void AddDummyData(ArrayList<Menu> menu){
        for (int i = 0; i<20; i++){
            menu.add(new Menu("Title #"+i,"URL #"+i));

        }
        mAdapter.notifyDataSetChanged();
    }


}