package deso2.doandinhhoang.appcaphe.utils;


import java.util.ArrayList;
import java.util.List;

import deso2.doandinhhoang.appcaphe.models.Beverage;
import deso2.doandinhhoang.appcaphe.models.Cart;

public class Utils {
    //Define a static instance of the Utils class
    private static Utils instance;


    private static List<Cart> cartList;
    private static List<Beverage> popularList;

    private Utils(){
        if (null==cartList){
            cartList = new ArrayList<>();
        }
        if (null==popularList){
            popularList = new ArrayList<>();
            initPopular();
        }
    }

    private void initPopular(){
        popularList.add(new Beverage("Cà phê Đen", "https://cdn3.iconfinder.com/data/icons/watercolorcafe/512/Latte.png", 20000));
        popularList.add(new Beverage("Cà phê Sữa", "https://tamingofthespoon.com/wp-content/uploads/2015/03/Vietnamese-Coffee-2.jpg"
                , 22000));
        popularList.add(new Beverage("Latte", "https://w7.pngwing.com/pngs/454/232/png-transparent-white-ceramic-mug-filled-with-coffee-illustration-latte-coffee-caffe-americano-cappuccino-doppio-coffee-latte-art-flat-white-cafe-au-lait-mocaccino-thumbnail.png"
                , 35000));
        popularList.add(new Beverage("Trà Matcha", "https://www.butteredsideupblog.com/wp-content/uploads/2022/07/Starbucks-Hot-Matcha-Green-Tea-Latte-Recipe-16-scaled.jpg"
                , 50000));
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

    public static List<Beverage> getPopularList() {
        return popularList;
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
