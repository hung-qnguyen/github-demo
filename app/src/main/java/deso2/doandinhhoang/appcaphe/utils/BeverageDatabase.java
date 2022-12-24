package deso2.doandinhhoang.appcaphe.utils;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import deso2.doandinhhoang.appcaphe.daos.BeverageDao;
import deso2.doandinhhoang.appcaphe.models.Beverage;

@Database(entities = {Beverage.class}, version = 1, exportSchema = false)
public abstract class BeverageDatabase extends RoomDatabase {

    public static final String DB_NAME = "beverage_db";
    private static BeverageDatabase instance;

    public abstract BeverageDao beverageDao();

    static BeverageDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (BeverageDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    BeverageDatabase.class, DB_NAME)
                            // Wipes and rebuilds instead of migrating 
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BeverageDao beverageDao;

        private PopulateDbAsyncTask(BeverageDatabase db) {
            beverageDao = db.beverageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            beverageDao.deleteAllBeverages();

            beverageDao.insertBeverage(new Beverage("Cà phê Đen", "https://cdn3.iconfinder.com/data/icons/watercolorcafe/512/Latte.png", 20000));
            beverageDao.insertBeverage(new Beverage("Cà phê Sữa", "https://tamingofthespoon.com/wp-content/uploads/2015/03/Vietnamese-Coffee-2.jpg"
                    , 22000));
            beverageDao.insertBeverage(new Beverage("Latte", "https://w7.pngwing.com/pngs/454/232/png-transparent-white-ceramic-mug-filled-with-coffee-illustration-latte-coffee-caffe-americano-cappuccino-doppio-coffee-latte-art-flat-white-cafe-au-lait-mocaccino-thumbnail.png"
                    , 35000));
            beverageDao.insertBeverage(new Beverage("Espresso", "https://g7.pngresmi.com/preview/797/1013/191/ristretto-espresso-caffe-americano-coffee-tea-hot-coffee-cup-png-clip-art-image-thumbnail.jpg"
                    , 22000));
            beverageDao.insertBeverage(new Beverage("Cacao", "https://img1.pngindir.com/20180331/zue/kisspng-chocolate-milk-hot-chocolate-animation-chocolat-5abfa3eaec8548.0516376715225087789688.jpg"
                    , 40000));
            beverageDao.insertBeverage(new Beverage("Bạc Xỉu", "https://img1.pngindir.com/20180703/orj/kisspng-vietnamese-iced-coffee-white-russian-coffee-milk-5b3bd0f39215e2.9278092715306467715984.jpg"
                    , 25000));
            beverageDao.insertBeverage(new Beverage("Sữa Tươi", "https://www.pngall.com/wp-content/uploads/2016/06/Milk-PNG-Clipart-180x180.png"
                    , 30000));
            beverageDao.insertBeverage(new Beverage("Trà Sữa Trân Châu", "https://tiermaker.com/images/templates/bubble-tea-in-toronto-697965/6979651612672833.jpg"
                    , 25000));
            beverageDao.insertBeverage(new Beverage("Trà Matcha", "https://www.butteredsideupblog.com/wp-content/uploads/2022/07/Starbucks-Hot-Matcha-Green-Tea-Latte-Recipe-16-scaled.jpg"
                    , 50000));
            beverageDao.insertBeverage(new Beverage("Trà Đào", "https://shottbeverages.com/wp-content/uploads/2018/11/iced-strawberry-white-tea_small-320x480-c-default.jpg"
                    , 35000));
            return null;
        }
    }

}
