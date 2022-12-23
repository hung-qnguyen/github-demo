package com.example.gitdemo.utils;

import com.example.gitdemo.models.Cart;
import com.example.gitdemo.models.Beverage;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    //Define a static instance of the Utils class
    public static final String CURRENCY = " đồng";
    private static Utils instance;

    private static List<Beverage> allDrinks;
    private static List<Cart> cartList;

    private Utils(){
        if (null==allDrinks){
            allDrinks = new ArrayList<>();
        }
        if (null==cartList){
            cartList = new ArrayList<>();
        }
    }


//    private void initDummyData(){
//        for (int i = 1; i<=20; i++){
//            allDrinks.add(new Beverage("Drink Title #"+i,"https://cdn3.iconfinder.com/data/icons/watercolorcafe/512/Latte.png"
//                    , 4000*i));
//        }
//    }

    public static synchronized Utils getInstance() {
        //make this class a singleton
        if (null!=instance){
            return instance;
        } else{
            instance = new Utils();
            return instance;
        }
    }

//    public static void setInstance(Utils instance) {
//        Utils.instance = instance;
//    }

    public static List<Beverage> getAllDrinks() {
        return allDrinks;
    }

    public static void setAllDrinks(List<Beverage> allDrinks) {
        Utils.allDrinks = allDrinks;
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
            if (c.getMenuItem().getDrinkName().equals(drinks.getDrinkName())) {
                existInCart = true;
            }
        }
        return existInCart;
    }
    public void addToCart(Cart cart){
        cartList.add(cart);
    }




}
