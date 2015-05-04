package com.group15.shoppingwithfriends.Database;

/**
 * Created by Travis on 2/9/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MOTHER = "mother";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_REPORT = "report";
    public static final String COLUMN_FAVORITE = "favorite";

    public static final String TABLE_FRIEND = "friend";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_FRIEND = "friend";

    public static final String TABLE_SALE = "sale";
    public static final String COLUMN_ITEM = "item";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_STORE = "store";
    public static final String COLUMN_DATE = "date";

    public static final String TABLE_INTEREST = "interest";
    //Will reuse COLUMN_USER
    //Will reuse COLUMN_ITEM
    //Will reuse COLUMN_PRICE
    public static final String COLUMN_DISTANCE = "distance";


    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_USER_TABLE = "create table "
            + TABLE_USER + "("
            + COLUMN_USERNAME + " text primary key not null, "
            + COLUMN_PASSWORD + " text not null, "
            + COLUMN_NAME + " text, "
            + COLUMN_PHONE + " text, "
            + COLUMN_EMAIL + " text, "
            + COLUMN_MOTHER + " text, "
            + COLUMN_STREET + " text, "
            + COLUMN_RATING + " integer, "
            + COLUMN_REPORT + " integer,"
            + COLUMN_FAVORITE + ");";

    private static final String CREATE_FRIEND_TABLE = "create table "
            + TABLE_FRIEND + "("
            + COLUMN_USER + " text not null, "
            + COLUMN_FRIEND + " text not null, "
            + "foreign key (" + COLUMN_USER + ") references " + TABLE_USER + " (" + COLUMN_USERNAME + "), "
            + "foreign key (" + COLUMN_FRIEND + ") references " + TABLE_USER + " (" + COLUMN_USERNAME + "), "
            + "primary key (" + COLUMN_USER + ", " + COLUMN_FRIEND + "));";

    private static final String CREATE_SALE_TABLE = "create table "
            + TABLE_SALE + "("
            + COLUMN_USER + " references " + TABLE_USER + " (" + COLUMN_USERNAME + "), "
            + COLUMN_ITEM + " text not null, "
            + COLUMN_PRICE + " real not null, "
            + COLUMN_LAT + " real not null, "
            + COLUMN_LONGITUDE + " real not null, "
            + COLUMN_STORE + " text not null, "
            + COLUMN_DATE + " text, "
            + "primary key (" + COLUMN_USER + ", " + COLUMN_ITEM + ", " + COLUMN_PRICE + ", " + COLUMN_LAT + ", " + COLUMN_LONGITUDE + ", " + COLUMN_STORE + ", " + COLUMN_DATE + "));";

    private static final String CREATE_INTEREST_TABLE = "create table "
            + TABLE_INTEREST + "("
            + COLUMN_USER + " references " + TABLE_USER + " (" + COLUMN_USERNAME + "), "
            + COLUMN_ITEM + " text not null, "
            + COLUMN_PRICE + " real not null, "
            + COLUMN_DISTANCE + " text not null, "
            + "primary key (" + COLUMN_USER + ", " + COLUMN_ITEM + ", " + COLUMN_PRICE + ", " + COLUMN_DISTANCE + "));";


    // make another table with 2 columns (user & friend) to make a 1:m
    //relationship between users and friends. Each column should be
    // a foreign key referencing the "username" column in TABLE_USERS.
    //the primary key should be a composite key of both columns (user and friend).

    /**
     * constructor for the userSQLiteHelper
     *
     * @param context
     */
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    /**
     * all that happens immediately after creation
     * @params database
     */
    public void onCreate(SQLiteDatabase database) {
        System.out.println(CREATE_FRIEND_TABLE);
        database.execSQL(CREATE_USER_TABLE);
        database.execSQL(CREATE_FRIEND_TABLE);
        database.execSQL(CREATE_SALE_TABLE);
        database.execSQL(CREATE_INTEREST_TABLE);
    }

    @Override
    /**
     * handles upgrades
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALE);
        onCreate(db);
    }

}