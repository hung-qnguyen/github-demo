package com.example.gitdemo.asyncTasks;

import android.os.AsyncTask;

import com.example.gitdemo.daos.BeverageDao;
import com.example.gitdemo.models.Beverage;

public class DeleteBeverageAsyncTask extends AsyncTask<Beverage, Void, Void> {
    private BeverageDao beverageDao;

    public DeleteBeverageAsyncTask(BeverageDao beverageDao) {
        this.beverageDao = beverageDao;
    }

    @Override
    protected Void doInBackground(Beverage... beverages) {
        beverageDao.deleteBeverage(beverages[0]);
        return null;
    }
}
