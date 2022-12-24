package com.example.gitdemo;



import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gitdemo.adapters.MenuAdapter;
import com.example.gitdemo.models.Beverage;
import com.example.gitdemo.utils.BeverageRepository;
import com.example.gitdemo.utils.BeverageViewModel;
import com.example.gitdemo.utils.Utils;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mSpinnerList = new ArrayList<>();
    private BeverageViewModel beverageViewModel;
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;
    private SearchView mSearchView;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = findViewById(R.id.search_view);
        mSpinner = findViewById(R.id.menu_spinner);



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
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setIconifiedByDefault(false);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                initSearchData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                initSearchData(s);
                return false;
            }
        });
initSpinner();
    }

    public void initSearchData(String s){
        beverageViewModel.getBeverageByName(s).observe(MainActivity.this, new Observer<List<Beverage>>() {
            @Override
            public void onChanged(List<Beverage> beverages) {
                mMenuAdapter.setMenu(beverages);
            }
        });
    }

    public void initSpinner(){
        mSpinnerList.add("Default");
        mSpinnerList.add("Sort by Name");
        mSpinnerList.add("Sort by Price Ascending");
        mSpinnerList.add("Sort by Price Descending");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this
                , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
                ,mSpinnerList);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(mSpinner.getSelectedItemPosition()){
                    case 0:
                        beverageViewModel.getAllBeverages().observe(MainActivity.this, new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(@Nullable List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);

                            }
                        });
                        break;
                    case 1:
                        beverageViewModel.sortBeveragesByName().observe(MainActivity.this, new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);
                            }
                        });
                        break;
                    case 2:
                        beverageViewModel.sortBeveragesByPriceAsc().observe(MainActivity.this, new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);
                            }
                        });
                        break;
                    case 3:
                        beverageViewModel.sortBeveragesByPriceDesc().observe(MainActivity.this, new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);
                            }
                        });
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}