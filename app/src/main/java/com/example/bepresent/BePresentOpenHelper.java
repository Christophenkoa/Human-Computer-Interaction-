package com.example.bepresent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BePresentOpenHelper extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME = "BePresent.db";
    public  static final int DATABASE_VERSION = 1;

    public BePresentOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BePresentDataBaseContract.UserEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
