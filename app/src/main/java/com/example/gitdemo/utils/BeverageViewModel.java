package com.example.gitdemo.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gitdemo.models.Beverage;

import java.util.List;

public class BeverageViewModel extends AndroidViewModel {
    private BeverageRepository repository;
    private LiveData<List<Beverage>> allBeverages;

    public BeverageViewModel(@NonNull Application application) {
        super(application);
        repository = new BeverageRepository(application);
        allBeverages = repository.getAllBeverages();
    }

    public void insertBeverage(Beverage beverage) {
        repository.insertBeverage(beverage);
    }

    public void updateBeverage(Beverage beverage) {
        repository.updateBeverage(beverage);
    }

    public void deleteBeverage(Beverage beverage) {
        repository.deleteBeverage(beverage);
    }

    public void deleteAllBeverages() {
        repository.deleteAllBeverages();
    }

    public LiveData<List<Beverage>> getAllBeverages() {
        return allBeverages;
    }

    public LiveData<List<Beverage>> sortBeveragesByName() {
        return repository.sortBeveragesByName();
    }

    public LiveData<List<Beverage>> sortBeveragesByPriceAsc() {
        return repository.sortBeveragesByPriceAsc();
    }

    public LiveData<List<Beverage>> sortBeveragesByPriceDesc() {
        return repository.sortBeveragesByPriceDesc();
    }

    public LiveData<List<Beverage>> getBeverageByName(String drinkName) {
        return repository.getBeverageByName(drinkName);
    }
}
