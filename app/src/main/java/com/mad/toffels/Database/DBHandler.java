package com.mad.toffels.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
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
            "CREATE TABLE " + OrderProfile.Profile.TABLE_NAME + " (" +
                    OrderProfile.Profile._ID + " INTEGER PRIMARY KEY," +
                    OrderProfile.Profile.COLUMN_1 + " TEXT," +
                    OrderProfile.Profile.COLUMN_2 + " TEXT," +
                    OrderProfile.Profile.COLUMN_3 + " TEXT," +
                    OrderProfile.Profile.COLUMN_4 + " TEXT," +
                    OrderProfile.Profile.COLUMN_5 + " TEXT," +
                    OrderProfile.Profile.COLUMN_6 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + OrderProfile.Profile.TABLE_NAME;

    public long addinfo(String username, String itemCode, String phoneNo ,String email , String DateOrder, String TimeOrder){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(OrderProfile.Profile.COLUMN_1, username);
        values.put(OrderProfile.Profile.COLUMN_2, itemCode);
        values.put(OrderProfile.Profile.COLUMN_3, phoneNo);
        values.put(OrderProfile.Profile.COLUMN_4, email);
        values.put(OrderProfile.Profile.COLUMN_5, DateOrder);
        values.put(OrderProfile.Profile.COLUMN_6, TimeOrder);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(OrderProfile.Profile.TABLE_NAME, null, values);

        return newRowId;

    }

    public boolean updateinfo(String username, String itemCode, String phoneNo ,String email , String DateOrder, String TimeOrder){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OrderProfile.Profile.COLUMN_2, itemCode);
        values.put(OrderProfile.Profile.COLUMN_3, phoneNo);
        values.put(OrderProfile.Profile.COLUMN_4, email);
        values.put(OrderProfile.Profile.COLUMN_5, DateOrder);
        values.put(OrderProfile.Profile.COLUMN_6, TimeOrder);

        // Which row to update, based on the title
        String selection = OrderProfile.Profile.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                OrderProfile.Profile.TABLE_NAME,
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

    public void deleteInfo(String username){

        SQLiteDatabase db = getWritableDatabase();

        String selection = OrderProfile.Profile.COLUMN_1 + " LIKE ?";

        String[] selectionArgs = { username };

        int deletedRows = db.delete(OrderProfile.Profile.TABLE_NAME, selection, selectionArgs);

    }

    public List readAllInfo(){

        String username = "nimesh";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                OrderProfile.Profile.COLUMN_1,
                OrderProfile.Profile.COLUMN_2,
                OrderProfile.Profile.COLUMN_3,
                OrderProfile.Profile.COLUMN_4,
                OrderProfile.Profile.COLUMN_5,
                OrderProfile.Profile.COLUMN_6
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = OrderProfile.Profile.COLUMN_1 + " = ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                OrderProfile.Profile.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                OrderProfile.Profile.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            long user = cursor.getLong(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_1));
            usernames.add(user);
        }
        cursor.close();
        return usernames;

    }

    //read
    public List readAllInfo(String username){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                OrderProfile.Profile.COLUMN_1,
                OrderProfile.Profile.COLUMN_2,
                OrderProfile.Profile.COLUMN_3,
                OrderProfile.Profile.COLUMN_4,
                OrderProfile.Profile.COLUMN_5,
                OrderProfile.Profile.COLUMN_6
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = OrderProfile.Profile.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { username };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                OrderProfile.Profile.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                OrderProfile.Profile.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List userinfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_1));
            String code = cursor.getString(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_2));
            String pone = cursor.getString(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_3));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_4));
            String Ddate = cursor.getString(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_5));
            String Tdate = cursor.getString(cursor.getColumnIndexOrThrow(OrderProfile.Profile.COLUMN_6));
            userinfo.add(user);
            userinfo.add(code);
            userinfo.add(pone);
            userinfo.add(email);
            userinfo.add(Ddate);
            userinfo.add(Tdate);
        }
        cursor.close();
        return userinfo;

    }



}
