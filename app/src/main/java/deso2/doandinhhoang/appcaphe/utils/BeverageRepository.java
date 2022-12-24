package deso2.doandinhhoang.appcaphe.utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import deso2.doandinhhoang.appcaphe.asyncTasks.DeleteAllBeverageAsyncTask;
import deso2.doandinhhoang.appcaphe.asyncTasks.DeleteBeverageAsyncTask;
import deso2.doandinhhoang.appcaphe.asyncTasks.InsertBeverageAsyncTask;
import deso2.doandinhhoang.appcaphe.asyncTasks.UpdateBeverageAsyncTask;
import deso2.doandinhhoang.appcaphe.daos.BeverageDao;
import deso2.doandinhhoang.appcaphe.models.Beverage;

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

    public LiveData<List<Beverage>> sortBeveragesByName() {
        return beverageDao.sortBeveragesByName();
    }
    public LiveData<List<Beverage>> sortBeveragesByPriceAsc() {
        return beverageDao.sortBeveragesByPriceAsc();
    }
    public LiveData<List<Beverage>> sortBeveragesByPriceDesc() {
        return beverageDao.sortBeveragesByPriceDesc();
    }

    public LiveData<List<Beverage>> getBeverageByName(String drinkName) {
        return beverageDao.getBeverageByName(drinkName);
    }
}
