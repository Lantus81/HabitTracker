package com.akristic.www.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.akristic.www.habittracker.data.HabitContract.HabitEntry;
import com.akristic.www.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitDbHelper(this);

        insertHabit();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_DURATION,
                HabitEntry.COLUMN_DATE,
                HabitEntry.COLUMN_DESIRABLE};

        Cursor cursor = readHabits(projection);
        logCursorData(cursor);
        cursor.close();
    }

    // inserting new row in table
    private void insertHabit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitEntry.COLUMN_NAME, "reading");
        contentValues.put(HabitEntry.COLUMN_DURATION, 3);
        contentValues.put(HabitEntry.COLUMN_DATE, "14/05/2017");
        contentValues.put(HabitEntry.COLUMN_DESIRABLE, HabitEntry.DESIRABLE_GOOD);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, contentValues);
        Log.v("MainActivity", "New row ID is " + newRowId);
    }

    // read data form table and return cursor
    private Cursor readHabits(String[] projection) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

    }

    //write cursor data in log message
    private void logCursorData(Cursor cursor) {
        String dataInCursorString = HabitEntry._ID + " - " +
                HabitEntry.COLUMN_NAME + " - " +
                HabitEntry.COLUMN_DURATION + " - " +
                HabitEntry.COLUMN_DATE + " - " +
                HabitEntry.COLUMN_DESIRABLE + "\n";
        // Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_NAME);
        int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DURATION);
        int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DATE);
        int desirableColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_DESIRABLE);
        //read all data in cursor
        while (cursor.moveToNext()) {

            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            String currentDuration = cursor.getString(durationColumnIndex);
            int currentDate = cursor.getInt(dateColumnIndex);
            int currentDesirable = cursor.getInt(desirableColumnIndex);
            // add data from row in table to string
            dataInCursorString += "\n" + currentID + " - " +
                    currentName + " - " +
                    currentDuration + " - " +
                    currentDate + " - " +
                    currentDesirable;
        }
        Log.v("MainActivity", "Cursor data is: " + dataInCursorString);

    }
}
