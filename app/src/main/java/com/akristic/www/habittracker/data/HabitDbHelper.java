package com.akristic.www.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.akristic.www.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Toni on 14.5.2017..
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habittracker.db";

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                        HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        HabitEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        HabitEntry.COLUMN_DURATION + " INTEGER NOT NULL DEFAULT 0, " +
                        HabitEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                        HabitEntry.COLUMN_DESIRABLE + " INTEGER NOT NULL DEFAULT 0);";


        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
