package com.example.gitdemo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private Beverage beverageItem;
    private int quantity;
    private float itemTotal;
    //TODO: Add ItemTotal and alreadyInCart to Cart Model


    public Cart(Beverage beverageItem, int quantity, float itemTotal) {
        this.beverageItem = beverageItem;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    protected Cart(Parcel in) {
        beverageItem = in.readParcelable(Beverage.class.getClassLoader());
        quantity = in.readInt();
        itemTotal = in.readFloat();
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

    public Beverage getMenuItem() {
        return beverageItem;
    }

    public void setMenuItem(Beverage beverageItem) {
        this.beverageItem = beverageItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(float itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(beverageItem, i);
        parcel.writeInt(quantity);
        parcel.writeFloat(itemTotal);
    }
}
