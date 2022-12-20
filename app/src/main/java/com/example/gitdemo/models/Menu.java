package com.example.gitdemo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private String drinkName, imgURL;
    private float price;

    public Menu(String drinkName, String imgURL, float price) {
        this.drinkName = drinkName;
        this.imgURL = imgURL;
        this.price = price;
    }


    protected Menu(Parcel in) {
        drinkName = in.readString();
        imgURL = in.readString();
        price = in.readFloat();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(drinkName);
        parcel.writeString(imgURL);
        parcel.writeFloat(price);
    }
}
