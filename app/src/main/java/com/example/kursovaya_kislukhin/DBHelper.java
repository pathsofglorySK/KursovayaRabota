package com.example.kursovaya_kislukhin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "library";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_BOOKS = "books";

    public static final String KEY_ID = "_id";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_TITLE = "title";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_DATE = "date";
    public static final String KEY_PAGES = "pages";
    public static final String KEY_LANGUAGE = "language";
    public static final String KEY_GENRE = "genre";
    public static final String KEY_COMMENT = "comment";


    public DBHelper( Context context,  String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_LOGIN + " TEXT," + KEY_PASSWORD + " TEXT" + ")");

        db.execSQL("CREATE TABLE " + TABLE_BOOKS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_AUTHOR
                + " TEXT," + KEY_GENRE + " TEXT," + KEY_DATE + " TEXT," + KEY_COMMENT
                + " TEXT," + KEY_LANGUAGE + " TEXT," + KEY_PAGES + " TEXT" + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_BOOKS);
        db.execSQL("drop table if exists " + TABLE_CONTACTS);
        onCreate(db);
    }
}
