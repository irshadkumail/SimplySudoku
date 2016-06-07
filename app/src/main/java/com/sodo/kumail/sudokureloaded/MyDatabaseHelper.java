package com.sodo.kumail.sudokureloaded;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kumail on 5/15/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Sudoku";

    public static final int DATABASE_VERSION=1;

    public MyDatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL("CREATE TABLE "+MyColumns.TABLE_NAME+" ("+MyColumns._ID+" INTEGER PRIMARY KEY,"+MyColumns.PUZZLE+" TEXT,"+MyColumns.USED+" INTEGER)");
    }
    public void onUpgrade(SQLiteDatabase database,int oldV,int newV )
    {

    }
}
