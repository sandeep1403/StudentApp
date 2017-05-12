package com.ingresos.user.studentapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.ingresos.user.studentapp.Model.Class_Model;
import com.ingresos.user.studentapp.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_MOBIL_NO = "user_mobile";
    private static final String COLUMN_USER_PASSWORD = "user_password";



    // Clasess table name
    private static final String TABLE_CLASS = "student";

    // Classes Table Columns names
    private static final String COLUMN_CLASS_NO = "class_no";
    private static final String COLUMN_CLASS_LOCATION= "class_location";
    private static final String COLUMN_CLASS_PAYMENT = "class_payment";
    private static final String COLUMN_CLASS_START_TIME= "class_start_time";
    private static final String COLUMN_CLASS_END_TIME = "class_end_time";
    private static final String COLUMN_CLASS_START_DATE= "class_start_date";
    private static final String COLUMN_CLASS_END_DATE = "class_end_date";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_MOBIL_NO + " INTEGER," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    // create table sql query
    private String CREATE_CLASSES_TABLE = "CREATE TABLE " + TABLE_CLASS + "("
            + COLUMN_CLASS_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CLASS_LOCATION + " TEXT,"
            + COLUMN_CLASS_PAYMENT + " TEXT," + COLUMN_CLASS_START_TIME + " TEXT," + COLUMN_CLASS_END_TIME + " TEXT," + COLUMN_CLASS_START_DATE + " TEXT,"+ COLUMN_CLASS_END_DATE + " TEXT" + ")";

    // drop table sql query
    private String DROP_CLASSES_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CLASSES_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_CLASSES_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_MOBIL_NO,user.getMobileno());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public  void addClasses(Class_Model class_model){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLASS_NO,class_model.getClass_no());
        values.put(COLUMN_CLASS_LOCATION,class_model.getClass_location());
        values.put(COLUMN_CLASS_PAYMENT,class_model.getClass_payment());
        values.put(COLUMN_CLASS_START_TIME,class_model.getClass_start_time());
        values.put(COLUMN_CLASS_END_TIME,class_model.getClass_end_time());
        values.put(COLUMN_CLASS_START_DATE,class_model.getClass_start_date());
        values.put(COLUMN_CLASS_END_DATE,class_model.getClass_end_date());


        // Inserting Row
        db.insert(TABLE_CLASS, null, values);
        db.close();

    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_MOBIL_NO
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setMobileno(cursor.getString(cursor.getColumnIndex(COLUMN_USER_MOBIL_NO)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Class_Model> getAllClassesList() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CLASS_NO,
                COLUMN_CLASS_LOCATION,
                COLUMN_CLASS_PAYMENT,
                COLUMN_CLASS_START_TIME,
                COLUMN_CLASS_END_TIME,
                COLUMN_CLASS_START_DATE,
                COLUMN_CLASS_END_DATE

        };
        // sorting orders
        String sortOrder =
                COLUMN_CLASS_NO + " ASC";
        List<Class_Model> classlist = new ArrayList<Class_Model>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_CLASS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Class_Model class_model = new Class_Model();
                class_model.setClass_no(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_NO)));
                class_model.setClass_location(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_LOCATION)));
                class_model.setClass_payment(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_PAYMENT)));
                class_model.setClass_start_time(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_START_TIME)));
                class_model.setClass_end_time(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_END_TIME)));
                class_model.setClass_start_date(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_START_DATE)));
                class_model.setClass_end_date(cursor.getString(cursor.getColumnIndex(COLUMN_CLASS_END_DATE)));

                // Adding user record to list
                classlist.add(class_model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return classlist;
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
