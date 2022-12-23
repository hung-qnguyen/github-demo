package com.example.gitdemo.utils;

import com.example.gitdemo.models.Cart;
import com.example.gitdemo.models.Beverage;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    //Define a static instance of the Utils class
    private static Utils instance;


    private static List<Cart> cartList;

    private Utils(){
        if (null==cartList){
            cartList = new ArrayList<>();
        }
    }



    public static synchronized Utils getInstance() {
        //make this class a singleton
        if (null!=instance){
            return instance;
        } else{
            instance = new Utils();
            return instance;
        }
    }


    public static List<Cart> getCartList() {
        return cartList;
    }

    public static void setCartList(List<Cart> cartList) {
        Utils.cartList = cartList;
    }

    public boolean handleAlreadyAdded(Beverage drinks) {
        boolean existInCart = false;
        for (Cart c : cartList) {
            if (c.getCartItem().getDrinkName().equals(drinks.getDrinkName())) {
                existInCart = true;
            }
        }
        return existInCart;
    }
    public void addToCart(Cart cart){
        cartList.add(cart);
    }




}
