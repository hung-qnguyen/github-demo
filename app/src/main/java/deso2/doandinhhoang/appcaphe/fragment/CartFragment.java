package deso2.doandinhhoang.appcaphe.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import deso2.doandinhhoang.appcaphe.R;
import deso2.doandinhhoang.appcaphe.adapters.CartAdapter;
import deso2.doandinhhoang.appcaphe.models.Cart;
import deso2.doandinhhoang.appcaphe.utils.Utils;

public class CartFragment extends Fragment implements CartAdapter.OnCartListener{
    private static final String TAG_CALC_TOTAL = "calculate_total";
    private int deliveryFee = 4000;
    private RecyclerView mCartRV;
    private CartAdapter mCartAdapter;
    private TextView tvSubTotal, tvDeliveryFee, tvTotal;
    private Button btnToMenu, btnCheckout;
    private View view;



    public CartFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        mCartRV = view.findViewById(R.id.cart_rv);
        mCartAdapter = new CartAdapter(getActivity(), this);
        mCartRV.setAdapter(mCartAdapter);
        mCartRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCartAdapter.setCartList(Utils.getInstance().getCartList());
        initViews();
        calculateTotals();
        return view;
    }

    private void initViews() {
        tvSubTotal = view.findViewById(R.id.sub_total);
        tvDeliveryFee = view.findViewById(R.id.delivery_fee);
        tvTotal = view.findViewById(R.id.total);
        btnToMenu = view.findViewById(R.id.btn_to_menu);
        btnCheckout = view.findViewById(R.id.btn_checkout);
    }

    private void calculateTotals() {
        List<Cart> cartList = Utils.getInstance().getCartList();
        int total = 0, subTotal = 0;
        for (Cart c : cartList) {
            subTotal += c.getItemTotal();
        }
        total = subTotal + deliveryFee;
        tvSubTotal.setText(String.valueOf(subTotal) + " đồng");
        tvDeliveryFee.setText(String.valueOf(deliveryFee) + " đồng");
        tvTotal.setText(String.valueOf(total) + " đồng");
        Log.d(TAG_CALC_TOTAL, "Sub Total: " + subTotal
                + "\nTotal: " + total);
        
    }

        @Override
        public void onCartClick(int position) {
            Utils.getInstance().getCartList().remove(Utils.getInstance().getCartList().get(position));
            mCartAdapter.notifyItemRemoved(position);
            calculateTotals();
        }
}