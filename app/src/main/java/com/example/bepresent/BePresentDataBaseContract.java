package com.example.bepresent;

import android.provider.BaseColumns;

public final class BePresentDataBaseContract {
    private BePresentDataBaseContract(){}

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USER_FULL_NAME = "user_full_name";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_USER_EMAIL = "user_email";
        public static final String COLUMN_USER_PASSWORD = "user_password";
        public static final String sId = _ID;

        //CREATE TABLE user ( user_full_name , user_name , user_email , user_password , user_gender)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        sId + "INTEGER PRIMARY KEY, " +
                        COLUMN_USER_FULL_NAME + "TEXT NOT NULL, " +
                        COLUMN_USER_NAME + "TEXT UNIQUE NOT NULL, " +
                        COLUMN_USER_EMAIL + "TEXT UNIQUE NOT NULL, " +
                        COLUMN_USER_PASSWORD + "TEXT NOT NULL)";

    }
}
