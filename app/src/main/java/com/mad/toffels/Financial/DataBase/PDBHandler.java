package com.mad.toffels.Financial.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

//payment details database
public class PDBHandler extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PaymentDB.db"; //define databse name

    public PDBHandler(Context context) {
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
            "CREATE TABLE " + PProfile.Payments.TABLE_NAME + " (" +
                    PProfile.Payments._ID + " INTEGER PRIMARY KEY," +
                    PProfile.Payments.COLUMN_1 + " TEXT," +
                    PProfile.Payments.COLUMN_2 + " TEXT," +
                    PProfile.Payments.COLUMN_3 + " TEXT," +
                    PProfile.Payments.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PProfile.Payments.TABLE_NAME;

    //add data
    public long addInfo (String holdername, String cardnum, String exdate, String cvvnum){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(PProfile.Payments.COLUMN_1, holdername);
        values.put(PProfile.Payments.COLUMN_2,  cardnum);
        values.put(PProfile.Payments.COLUMN_3, exdate);
        values.put(PProfile.Payments.COLUMN_4, cvvnum);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(PProfile.Payments.TABLE_NAME, null, values);

        return newRowId;

    }

    //update data
    public Boolean updateInfo (String holdername, String cardnum, String exdate, String cvvnum){

        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(PProfile.Payments.COLUMN_2, cardnum);
        values.put(PProfile.Payments.COLUMN_3, exdate);
        values.put(PProfile.Payments.COLUMN_4, cvvnum);

        // Which row to update, based on the title
        String selection = PProfile.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };

        int count = db.update(
                PProfile.Payments.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >=1 ) {
            return true;
        }
        else {
            return false;
        }

    }

    //delete data
    public void deleteInfo (String holdername){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = PProfile.Payments.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { holdername };
        // Issue SQL statement.
        int deletedRows = db.delete(PProfile.Payments.TABLE_NAME, selection, selectionArgs);


    }


    //view all data
    public List readAllInfo (String holdername){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                PProfile.Payments.COLUMN_1,
                PProfile.Payments.COLUMN_2,
                PProfile.Payments.COLUMN_3,
                PProfile.Payments.COLUMN_4
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = PProfile.Payments.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { holdername };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                PProfile.Payments.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                PProfile.Payments.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List PaymenetDet = new ArrayList<>();
        while(cursor.moveToNext()) {
            String hname = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_1));
            String cardno = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_2));
            String edate = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_3));
            String cvvno = cursor.getString(cursor.getColumnIndexOrThrow(PProfile.Payments.COLUMN_4));
            PaymenetDet.add(hname);//0
            PaymenetDet.add(cardno);//1
            PaymenetDet.add(edate);//2
            PaymenetDet.add(cvvno);//3
        }
        cursor.close();
        return PaymenetDet;
    }



}
