package deso2.doandinhhoang.appcaphe.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private Beverage cartItem;
    private int quantity;
    private int itemTotal;
    //TODO: Add ItemTotal and alreadyInCart to Cart Model


    public Cart(Beverage cartItem, int quantity, int itemTotal) {
        this.cartItem = cartItem;
        this.quantity = quantity;
        this.itemTotal = itemTotal;
    }

    protected Cart(Parcel in) {
        cartItem = in.readParcelable(Beverage.class.getClassLoader());
        quantity = in.readInt();
        itemTotal = in.readInt();
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

    public Beverage getCartItem() {
        return cartItem;
    }

    public void setCartItem(Beverage cartItem) {
        this.cartItem = cartItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(cartItem, i);
        parcel.writeInt(quantity);
        parcel.writeInt(itemTotal);
    }
}
