package deso2.doandinhhoang.appcaphe.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import deso2.doandinhhoang.appcaphe.models.Beverage;

@Dao
public interface BeverageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeverage(Beverage beverage);

    @Update
    void updateBeverage(Beverage beverage);

    @Delete
    void deleteBeverage(Beverage beverage);

    @Query("DELETE FROM beverage_table")
    void deleteAllBeverages();


    @Query("SELECT * FROM beverage_table")
    LiveData<List<Beverage>> getAllBeverages();

    @Query("SELECT * FROM beverage_table ORDER BY drink_name ASC")
    LiveData<List<Beverage>> sortBeveragesByName();

    @Query("SELECT * FROM beverage_table ORDER BY price ASC")
    LiveData<List<Beverage>> sortBeveragesByPriceAsc();

    @Query("SELECT * FROM beverage_table ORDER BY price DESC")
    LiveData<List<Beverage>> sortBeveragesByPriceDesc();

    @Query("SELECT * FROM beverage_table WHERE drink_name LIKE '%'||:drinkName||'%'")
    LiveData<List<Beverage>> getBeverageByName(String drinkName);
}
