package com.example.gitdemo.asyncTasks;

import android.os.AsyncTask;

import com.example.gitdemo.daos.BeverageDao;
import com.example.gitdemo.models.Beverage;

public class UpdateBeverageAsyncTask extends AsyncTask<Beverage, Void, Void> {
    private BeverageDao beverageDao;

    public UpdateBeverageAsyncTask(BeverageDao beverageDao) {
        this.beverageDao = beverageDao;
    }

    @Override
    protected Void doInBackground(Beverage... beverages) {
        beverageDao.updateBeverage(beverages[0]);
        return null;
    }
}
