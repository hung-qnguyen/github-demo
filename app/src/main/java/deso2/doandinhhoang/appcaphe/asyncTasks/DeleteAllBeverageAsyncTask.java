package deso2.doandinhhoang.appcaphe.asyncTasks;

import android.os.AsyncTask;

import deso2.doandinhhoang.appcaphe.daos.BeverageDao;


public class DeleteAllBeverageAsyncTask extends AsyncTask<Void, Void, Void> {
    private BeverageDao beverageDao;

    public DeleteAllBeverageAsyncTask(BeverageDao beverageDao) {
        this.beverageDao = beverageDao;
    }

    @Override
    protected Void doInBackground(Void...voids) {
        beverageDao.deleteAllBeverages();
        return null;
    }
}
