
package alex.com.mycolectioncars.api.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Storage extends SQLiteOpenHelper {

    private static final String TAG = Storage.class.getSimpleName();

    @Inject
    public Storage(Context context) {
        super(context, "cars_db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, car.getTitle());
        values.put(PREVIEW_DESCRIPTION, car.getPreviewDescription());
        values.put(DETAIL_DESCRIPTION, car.getDetailDescription());
        values.put(IMAGE_URL, car.getImage());

        try {
            db.insert(TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }

        db.close();
    }

    public List<Car> getSavedCars() {
        List<Car> carList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery(SELECT_QUERY, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            Car car = new Car();
                            car.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                            car.setPreviewDescription(cursor.getString(cursor.getColumnIndex(PREVIEW_DESCRIPTION)));
                            car.setDetailDescription(cursor.getString(cursor.getColumnIndex(DETAIL_DESCRIPTION)));
                            car.setImage(cursor.getString(cursor.getColumnIndex(IMAGE_URL)));

                            carList.add(car);

                        } while (cursor.moveToNext());
                    }
                }
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return carList;
    }

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PREVIEW_DESCRIPTION = "previewDescription";
    private static final String DETAIL_DESCRIPTION = "detailDescription";
    private static final String IMAGE_URL = "imageUrl";
    private static final String TABLE_NAME = "cars";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " integer primary key autoincrement not null," +
            TITLE + " text not null," +
            PREVIEW_DESCRIPTION + " text not null," +
            DETAIL_DESCRIPTION + " text not null," +
            IMAGE_URL + " text not null)";
}
