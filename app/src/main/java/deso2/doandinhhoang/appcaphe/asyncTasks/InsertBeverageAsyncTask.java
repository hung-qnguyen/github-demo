package deso2.doandinhhoang.appcaphe.asyncTasks;

import android.os.AsyncTask;

import deso2.doandinhhoang.appcaphe.daos.BeverageDao;
import deso2.doandinhhoang.appcaphe.models.Beverage;


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
