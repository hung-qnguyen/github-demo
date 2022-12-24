package deso2.doandinhhoang.appcaphe.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beverage_table")
public class Beverage implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int beverageId;

    @ColumnInfo(name = "drink_name")
    private String drinkName;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name="image_URL")
    private String imgURL;

    public Beverage(String drinkName, String imgURL, int price) {
        this.drinkName = drinkName;
        this.price = price;
        this.imgURL = imgURL;
    }

    protected Beverage(Parcel in) {
        beverageId = in.readInt();
        drinkName = in.readString();
        price = in.readInt();
        imgURL = in.readString();
    }

    public static final Creator<Beverage> CREATOR = new Creator<Beverage>() {
        @Override
        public Beverage createFromParcel(Parcel in) {
            return new Beverage(in);
        }

        @Override
        public Beverage[] newArray(int size) {
            return new Beverage[size];
        }
    };

    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(beverageId);
        parcel.writeString(drinkName);
        parcel.writeInt(price);
        parcel.writeString(imgURL);
    }
}
