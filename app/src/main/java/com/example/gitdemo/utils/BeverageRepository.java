package com.example.gitdemo.utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.gitdemo.asyncTasks.DeleteAllBeverageAsyncTask;
import com.example.gitdemo.asyncTasks.DeleteBeverageAsyncTask;
import com.example.gitdemo.asyncTasks.InsertBeverageAsyncTask;
import com.example.gitdemo.asyncTasks.UpdateBeverageAsyncTask;
import com.example.gitdemo.daos.BeverageDao;
import com.example.gitdemo.models.Beverage;

import java.util.List;

public class BeverageRepository {
    private BeverageDao beverageDao;
    private LiveData<List<Beverage>> allBeverages;


    public BeverageRepository(Application application) {
        BeverageDatabase database = BeverageDatabase.getInstance(application);
        beverageDao = database.beverageDao();
        allBeverages = beverageDao.getAllBeverages();
    }

    public void insertBeverage(Beverage beverage) {
        new InsertBeverageAsyncTask(beverageDao).execute(beverage);
    }

    public void updateBeverage(Beverage beverage) {
        new UpdateBeverageAsyncTask(beverageDao).execute(beverage);
    }

    public void deleteBeverage(Beverage beverage) {
        new DeleteBeverageAsyncTask(beverageDao).execute(beverage);
    }

    public void deleteAllBeverages() {
        new DeleteAllBeverageAsyncTask(beverageDao).execute();
    }

    public LiveData<List<Beverage>> getAllBeverages() {
        return allBeverages;
    }
}
