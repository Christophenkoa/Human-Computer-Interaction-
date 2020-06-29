package com.example.bepresent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BePresentOpenHelper extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME = "BePresent.db";
    public  static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "user";
    public static final String COL_2 = "user_full_name";
    public static final String COL_3 = "user_name";
    public static final String COL_4 = "user_email";
    public static final String COL_5 = "user_password";
    public static final String COL_1 = "ID";

    public BePresentOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (ID INTEGER PRIMARY KEY AUTOINCREMENT , user_full_name TEXT NOT NULL,user_name TEXT UNIQUE NOT NULL, user_email TEXT UNIQUE NOT NULL, user_password TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String userFullName , String userName , String userEmail , String userPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_full_name",userFullName);
        contentValues.put("user_name",userName);
        contentValues.put("user_email",userName);
        contentValues.put("user_password",userPassword);
        long res = db.insert("user",null ,contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String email , String password) {
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_4 + "=?" + " and " + COL_5 + "=?";
        String[] selectionArgs = { email , password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection, selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0) {
            return true;
        }else {
            return false;
        }
    }
}
