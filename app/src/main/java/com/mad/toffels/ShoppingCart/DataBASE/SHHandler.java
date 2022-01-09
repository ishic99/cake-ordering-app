package com.mad.toffels.ShoppingCart.DataBASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.mad.toffels.AddItems.Database.ItemProfile;

import java.util.ArrayList;
import java.util.List;

public class SHHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataBASE.db";

    public SHHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ShopingProfile.Shop.TABLE_NAME + " (" +
                    ShopingProfile.Shop._ID + " INTEGER PRIMARY KEY," +
                    ShopingProfile.Shop.COLUMN_1 + " TEXT," +
                    ShopingProfile.Shop.COLUMN_2 + " TEXT," +
                    ShopingProfile.Shop.COLUMN_3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ShopingProfile.Shop.TABLE_NAME;

    //Add
    public long add(String qty, String address, String total){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ShopingProfile.Shop.COLUMN_1, qty);
        values.put(ShopingProfile.Shop.COLUMN_2, address);
        values.put(ShopingProfile.Shop.COLUMN_3, total);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ShopingProfile.Shop.TABLE_NAME, null, values);

        return newRowId;
    }
    //Update
    public boolean update(String qty, String address, String total){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ShopingProfile.Shop.COLUMN_2, address);
        values.put(ShopingProfile.Shop.COLUMN_3, total);

        String selection = ShopingProfile.Shop.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { qty };

        int count = db.update(
                ShopingProfile.Shop.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1){
            return true;
        }
        else {
            return false;
        }
    }

    //delete
    public void deleteINFO(String qty){

        SQLiteDatabase db = getWritableDatabase();

        String selection = ShopingProfile.Shop.COLUMN_1 + " LIKE ?";

        String[] selectionArgs = { qty };

        int deletedRows = db.delete(ShopingProfile.Shop.TABLE_NAME, selection, selectionArgs);
    }
    //read
    public List read(){

        String qty ="4";
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ShopingProfile.Shop.COLUMN_1,
                ShopingProfile.Shop.COLUMN_2,
                ShopingProfile.Shop.COLUMN_3
        };
        // Filter results WHERE "title" = 'My Title'
        String selection = ShopingProfile.Shop.COLUMN_1 + " = ?";
        String[] selectionArgs = { qty };

        String sortOrder =
                ShopingProfile.Shop.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ShopingProfile.Shop.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List qtys = new ArrayList<>();
        while(cursor.moveToNext()) {
            long QTY = cursor.getLong(cursor.getColumnIndexOrThrow(ShopingProfile.Shop.COLUMN_1));
            qtys.add(QTY);
        }
        cursor.close();
        return qtys;
    }
    //readALL
    public List read(String qty){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ShopingProfile.Shop.COLUMN_1,
                ShopingProfile.Shop.COLUMN_2,
                ShopingProfile.Shop.COLUMN_3
        };
        String selection = ShopingProfile.Shop.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { qty };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ShopingProfile.Shop.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ShopingProfile.Shop.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List use = new ArrayList<>();
        while(cursor.moveToNext()) {
            String QT = cursor.getString(cursor.getColumnIndexOrThrow(ShopingProfile.Shop.COLUMN_1));
            String AD = cursor.getString(cursor.getColumnIndexOrThrow(ShopingProfile.Shop.COLUMN_2));
            String TOT = cursor.getString(cursor.getColumnIndexOrThrow(ShopingProfile.Shop.COLUMN_3));

            use.add(QT);
            use.add(AD);
            use.add(TOT);
        }
        cursor.close();
        return use;
    }
}
