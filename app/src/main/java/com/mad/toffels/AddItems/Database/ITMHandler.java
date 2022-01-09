package com.mad.toffels.AddItems.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.mad.toffels.Database.OrderProfile;

import java.util.ArrayList;
import java.util.List;

public class ITMHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataBase.db";

    public ITMHandler(Context context) {
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
            "CREATE TABLE " + ItemProfile.Item.TABLE_NAME + " (" +
                    ItemProfile.Item._ID + " INTEGER PRIMARY KEY," +
                    ItemProfile.Item.COLUMN_1 + " TEXT," +
                    ItemProfile.Item.COLUMN_2 + " TEXT," +
                    ItemProfile.Item.COLUMN_3 + " TEXT," +
                    ItemProfile.Item.COLUMN_4 + " TEXT," +
                    ItemProfile.Item.COLUMN_5 + " TEXT," +
                    ItemProfile.Item.COLUMN_6 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ItemProfile.Item.TABLE_NAME;

    public long addInfo(String itemcode, String cakename, String weight, String sheap, String flavuor, String Price ){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ItemProfile.Item.COLUMN_1, itemcode);
        values.put(ItemProfile.Item.COLUMN_2, cakename);
        values.put(ItemProfile.Item.COLUMN_3, weight);
        values.put(ItemProfile.Item.COLUMN_4, sheap);
        values.put(ItemProfile.Item.COLUMN_5, flavuor);
        values.put(ItemProfile.Item.COLUMN_6, Price);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ItemProfile.Item.TABLE_NAME, null, values);

        return newRowId;
    }

    //update details
    public boolean updateInfo(String itemcode, String cakename, String weight, String sheap, String flavuor, String Price){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemProfile.Item.COLUMN_2, cakename);
        values.put(ItemProfile.Item.COLUMN_3, weight);
        values.put(ItemProfile.Item.COLUMN_4, sheap);
        values.put(ItemProfile.Item.COLUMN_5, flavuor);
        values.put(ItemProfile.Item.COLUMN_6, Price);

        // Which row to update, based on the title
        String selection = ItemProfile.Item.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { itemcode };

        int count = db.update(
                ItemProfile.Item.TABLE_NAME,
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

    public void deleteinfo(String itemcode ){

        SQLiteDatabase db = getWritableDatabase();

        String selection = ItemProfile.Item.COLUMN_1 + " LIKE ?";

        String[] selectionArgs = { itemcode };

        int deletedRows = db.delete(ItemProfile.Item.TABLE_NAME, selection, selectionArgs);

    }

    public List readAllinfo(){

        String itemcode = "009";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ItemProfile.Item.COLUMN_1,
                ItemProfile.Item.COLUMN_2,
                ItemProfile.Item.COLUMN_3,
                ItemProfile.Item.COLUMN_4,
                ItemProfile.Item.COLUMN_5,
                ItemProfile.Item.COLUMN_6
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ItemProfile.Item.COLUMN_1 + " = ?";
        String[] selectionArgs = { itemcode };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ItemProfile.Item.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ItemProfile.Item.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List itemcodes = new ArrayList<>();
        while(cursor.moveToNext()) {
            long users = cursor.getLong(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_1));
            itemcodes.add(users);
        }
        cursor.close();
        return itemcodes;
    }

    //read
    public List readAllinfo(String itemcode){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ItemProfile.Item.COLUMN_1,
                ItemProfile.Item.COLUMN_2,
                ItemProfile.Item.COLUMN_3,
                ItemProfile.Item.COLUMN_4,
                ItemProfile.Item.COLUMN_5,
                ItemProfile.Item.COLUMN_6
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ItemProfile.Item.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { itemcode };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ItemProfile.Item.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ItemProfile.Item.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List userinfo1 = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemcod = cursor.getString(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_1));
            String cakeName = cursor.getString(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_2));
            String Weight = cursor.getString(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_3));
            String Shape = cursor.getString(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_4));
            String Flavour = cursor.getString(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_5));
            String Price = cursor.getString(cursor.getColumnIndexOrThrow(ItemProfile.Item.COLUMN_6));
            userinfo1.add(itemcod);
            userinfo1.add(cakeName);
            userinfo1.add(Weight);
            userinfo1.add(Shape);
            userinfo1.add(Flavour);
            userinfo1.add(Price);
        }
        cursor.close();
        return userinfo1;

    }


}
