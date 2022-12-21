package com.example.gitdemo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private Menu menuItem;
    private int quantity;
    //TODO: Add ItemTotal and alreadyInCart to Cart Model

    public Cart(Menu menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    protected Cart(Parcel in) {
        menuItem = in.readParcelable(Menu.class.getClassLoader());
        quantity = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public Menu getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Menu menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(menuItem, i);
        parcel.writeInt(quantity);
    }
}
