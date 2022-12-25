package deso2.doandinhhoang.appcaphe.fragment;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import deso2.doandinhhoang.appcaphe.R;
import deso2.doandinhhoang.appcaphe.adapters.PopularAdapter;
import deso2.doandinhhoang.appcaphe.models.Beverage;
import deso2.doandinhhoang.appcaphe.utils.Utils;


public class HomeFragment extends Fragment {
//    private List<Beverage> popularDrinks = new ArrayList<>();
    private PopularAdapter popularAdapter;
    private RecyclerView recyclerView;
    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.popular_rv);
        popularAdapter = new PopularAdapter(getActivity());
        recyclerView.setAdapter(popularAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), HORIZONTAL,false));
        popularAdapter.setMenu(Utils.getInstance().getPopularList());
        return view;
    }
}