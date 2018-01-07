package com.example.thor.modus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by thor on 1/6/2018.
 */

public class FoodDbAdapter {

    private static final String DATABASE_NAME = "fooditems.db";
    private static final int DATABASE_VERSION = 1;

    private static final String FOOD_TABLE = "food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_DATE = "date";

    private String [] allColumns = { COLUMN_ID, COLUMN_TITLE, COLUMN_WEIGHT, COLUMN_CATEGORY, COLUMN_DATE};

    private static final String CREATE_TABLE_FOOD = "create table " + FOOD_TABLE + " ( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_WEIGHT + " text not null, "
            + COLUMN_CATEGORY + " text not null, "
            + COLUMN_DATE + ");";

    private SQLiteDatabase sqlDB;
    private Context context;

    private FoodDbHelper foodDbHelper;

    public FoodDbAdapter(Context ctxt) {
        context = ctxt;
    }

    public FoodDbAdapter open() throws android.database.SQLException {
        foodDbHelper = new FoodDbHelper(context);
        sqlDB = foodDbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        foodDbHelper.close();
    }

    public ArrayList<FoodItem> getAllFoodItems() {
        ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

        Cursor cursor = sqlDB.query(FOOD_TABLE, allColumns, null, null, null, null, COLUMN_DATE+" DESC");

        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            FoodItem foodItem = cursorToFoodItem(cursor);
            foodItems.add(foodItem);
        }

        cursor.close();

        return foodItems;
    }

    public FoodItem createFoodItem(String title, String message, FoodItem.Category category, String expiryDateInMilli) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_WEIGHT, message);
        values.put(COLUMN_CATEGORY, category.name());
        values.put(COLUMN_DATE, expiryDateInMilli);

        long insertID = sqlDB.insert(FOOD_TABLE, null, values);

        Cursor cursor = sqlDB.query(FOOD_TABLE, allColumns, COLUMN_ID + " = " + insertID, null, null, null, null);

        cursor.moveToFirst();
        FoodItem foodItem = cursorToFoodItem(cursor);
        cursor.close();

        return foodItem;
    }

    public long updateFoodItem(long idToUpdate, String title, String message, FoodItem.Category category, String expiryDateInMilli) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_WEIGHT, message);
        values.put(COLUMN_CATEGORY, category.name());
        values.put(COLUMN_DATE, expiryDateInMilli);

        return sqlDB.update(FOOD_TABLE, values, COLUMN_ID + " = " + idToUpdate, null);

    }

    public long deleteFoodItem(long idToDelete) {
        return sqlDB.delete(FOOD_TABLE, COLUMN_ID + " = " + idToDelete, null);
    }

    public void deleteAllFoodItem() {
        sqlDB.execSQL("delete from " + FOOD_TABLE);
    }

    private FoodItem cursorToFoodItem(Cursor cursor) {
        FoodItem foodItem = new FoodItem(cursor.getString(1), cursor.getString(2), FoodItem.Category.valueOf(cursor.getString(3)), cursor.getLong(0), cursor.getString(4));
        return foodItem;
    }

    public ArrayList<FoodItem> getFoodItemsByDate(String date) {
        ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();

        Log.i("date asking: ", date);
        String query = "SELECT * FROM "+FOOD_TABLE+" WHERE "+COLUMN_DATE+"='" + date+"'";

        Cursor  cursor = sqlDB.rawQuery(query,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        //return cursor;

        //Cursor cursor = sqlDB.query(FOOD_TABLE, allColumns, COLUMN_DATE + "="+ date,null, null, null, null);

        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()) {
            FoodItem foodItem = cursorToFoodItem(cursor);
            foodItems.add(foodItem);
        }

        cursor.close();

        return foodItems;
    }

    private  static class FoodDbHelper extends SQLiteOpenHelper {

        FoodDbHelper(Context ctxt) {
            super(ctxt, DATABASE_NAME, null, DATABASE_VERSION );
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE_FOOD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w(FoodDbHelper.class.getName(), "upgraging loose data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

}
