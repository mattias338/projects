package com.banken.dbtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OtherDbActions {


    private final SQLiteDatabase wdb;
    private final SQLiteDatabase rdb;

    public OtherDbActions(FeedReaderDbHelper mDbHelper) {
        wdb = mDbHelper.getWritableDatabase();
        rdb = mDbHelper.getReadableDatabase();
    }

    public void storeData() {


        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, "id: " + Math.random());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, "title" + Math.random());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = wdb.insert(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                null,
                values);
    }

    public String readData() {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
        };

        Cursor c = rdb.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
                );

        c.moveToFirst();
        StringBuilder sb = new StringBuilder();
        while (!c.isLast()) {
            sb.append("id:");
            sb.append(c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry._ID)));
            sb.append(", random: ");
            sb.append(c.getString(c.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE)));
            sb.append(System.getProperty("line.separator"));
            c.moveToNext();
        }

        return sb.toString();
    }
}
