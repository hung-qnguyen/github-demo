package com.example.gitdemo.asyncTasks;

import android.os.AsyncTask;

import com.example.gitdemo.daos.BeverageDao;
import com.example.gitdemo.models.Beverage;

public class InsertBeverageAsyncTask extends AsyncTask<Beverage, Void, Void> {
    private BeverageDao beverageDao;

    public InsertBeverageAsyncTask(BeverageDao beverageDao) {
        this.beverageDao = beverageDao;
    }

    @Override
    protected Void doInBackground(Beverage... beverages) {
        beverageDao.insertBeverage(beverages[0]);
        return null;
    }
}
