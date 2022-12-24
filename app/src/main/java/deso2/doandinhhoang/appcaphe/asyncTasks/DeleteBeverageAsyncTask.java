package deso2.doandinhhoang.appcaphe.asyncTasks;

import android.os.AsyncTask;

import deso2.doandinhhoang.appcaphe.daos.BeverageDao;
import deso2.doandinhhoang.appcaphe.models.Beverage;

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
