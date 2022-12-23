package com.example.gitdemo.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gitdemo.models.Beverage;

import java.util.List;

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

//    @Query("SELECT * FROM beverage_table ORDER BY drink_name ASC")
//    LiveData<List<Beverage>> orderBeveragesByName();

//    @Query("SELECT * FROM beverage_table /*LIKE*/WHERE drink_name =:drinkName")
//            List<Beverage> getBeverageByName(String drinkName);
}
