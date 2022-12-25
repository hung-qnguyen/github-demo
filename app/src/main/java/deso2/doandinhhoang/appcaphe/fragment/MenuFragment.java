package deso2.doandinhhoang.appcaphe.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import deso2.doandinhhoang.appcaphe.R;
import deso2.doandinhhoang.appcaphe.adapters.MenuAdapter;
import deso2.doandinhhoang.appcaphe.models.Beverage;
import deso2.doandinhhoang.appcaphe.utils.BeverageViewModel;


public class MenuFragment extends Fragment {

    private ArrayList<String> mSpinnerList = new ArrayList<>();
    private BeverageViewModel beverageViewModel;
    private RecyclerView mRecyclerView;
    private MenuAdapter mMenuAdapter;
    private SearchView mSearchView;
    private Spinner mSpinner;

    public MenuFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mSearchView = view.findViewById(R.id.search_view);
        mSpinner = view.findViewById(R.id.menu_spinner);



        mRecyclerView = view.findViewById(R.id.menu_rv);
        mMenuAdapter = new MenuAdapter(getActivity());
        mRecyclerView.setAdapter(mMenuAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        beverageViewModel = ViewModelProviders.of(getActivity()).get(BeverageViewModel.class);
        beverageViewModel.getAllBeverages().observe(getActivity(), new Observer<List<Beverage>>() {
            @Override
            public void onChanged(@Nullable List<Beverage> beverages) {
                mMenuAdapter.setMenu(beverages);

            }
        });
        mSearchView.setSubmitButtonEnabled(true);
//        mSearchView.setIconifiedByDefault(false);

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
        return view;
    }

    public void initSearchData(String s){
        beverageViewModel.getBeverageByName(s).observe(getActivity(), new Observer<List<Beverage>>() {
            @Override
            public void onChanged(List<Beverage> beverages) {
                mMenuAdapter.setMenu(beverages);
            }
        });
    }

    public void initSpinner(){
        mSpinnerList.add("Mặc Định");
        mSpinnerList.add("A đến Z");
        mSpinnerList.add("Giá thấp dến cao");
        mSpinnerList.add("Giá cao đến thấp");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity()
                , androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
                ,mSpinnerList);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(mSpinner.getSelectedItemPosition()){
                    case 0:
                        beverageViewModel.getAllBeverages().observe(getActivity(), new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(@Nullable List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);

                            }
                        });
                        break;
                    case 1:
                        beverageViewModel.sortBeveragesByName().observe(getActivity(), new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);
                            }
                        });
                        break;
                    case 2:
                        beverageViewModel.sortBeveragesByPriceAsc().observe(getActivity(), new Observer<List<Beverage>>() {
                            @Override
                            public void onChanged(List<Beverage> beverages) {
                                mMenuAdapter.setMenu(beverages);
                            }
                        });
                        break;
                    case 3:
                        beverageViewModel.sortBeveragesByPriceDesc().observe(getActivity(), new Observer<List<Beverage>>() {
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
