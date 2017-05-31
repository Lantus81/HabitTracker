package com.akristic.www.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Toni on 14.5.2017..
 */

public final class HabitContract {
    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {
        //table name
        public final static String TABLE_NAME = "habits";
        // columns names
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "habit_name";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESIRABLE = "desirable";
        // desirable is godd or bad
        public static final int DESIRABLE_GOOD = 0;
        public static final int DESIRABLE_BAD = 1;
    }
}