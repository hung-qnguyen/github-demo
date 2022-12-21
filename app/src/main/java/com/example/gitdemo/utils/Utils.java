package com.example.gitdemo.utils;

import com.example.gitdemo.models.Cart;
import com.example.gitdemo.models.Menu;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    //Define a static instance of the Utils class
    private static Utils instance;

    private static List<Menu> allDrinks;
    private static List<Cart> cartList;

    private Utils(){
        if (null==allDrinks){
            allDrinks = new ArrayList<>();
//            initData();
            initDummyData();
        }
        if (null==cartList){
            cartList = new ArrayList<>();
        }
    }

    private void initData() {
        //Add Initial Data
    }

    private void initDummyData(/*List<Menu> allDrinks*/){
        for (int i = 1; i<=20; i++){
            allDrinks.add(new Menu("Drink Title #"+i,"https://cdn3.iconfinder.com/data/icons/watercolorcafe/512/Latte.png"
                    , 4000*i));
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

//    public static void setInstance(Utils instance) {
//        Utils.instance = instance;
//    }

    public static List<Menu> getAllDrinks() {
        return allDrinks;
    }

    public static void setAllDrinks(List<Menu> allDrinks) {
        Utils.allDrinks = allDrinks;
    }

    public static List<Cart> getCartList() {
        return cartList;
    }

    public static void setCartList(List<Cart> cartList) {
        Utils.cartList = cartList;
    }
}
