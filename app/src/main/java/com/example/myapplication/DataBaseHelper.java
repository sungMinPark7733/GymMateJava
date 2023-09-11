package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_GENDER = "USER_GENDER";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_USER_HEIGHT = "USER_HEIGHT";
    public static final String COLUMN_USER_WEIGHT = "USER_WEIGHT";
    public static final String COLUMN_USER_GOAL = "USER_GOAL";
    public static final String COLUMN_USER_DAYS = "USER_DAYS";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " +
                    USER_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_GENDER + " TEXT, " +
                    COLUMN_USER_AGE + " INT, " +
                    COLUMN_USER_HEIGHT + " INT, " +
                    COLUMN_USER_WEIGHT + " INT, " +
                    COLUMN_USER_GOAL + " TEXT, " +
                    COLUMN_USER_DAYS + " TEXT)";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }


    // this is called if the database version number changes. It prevents previous users app from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Add user information into DB
    public boolean addOne(UserModel userModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_EMAIL, userModel.getEmail());
        cv.put(COLUMN_USER_NAME, userModel.getName());
        cv.put(COLUMN_USER_GENDER, userModel.getGender());
        cv.put(COLUMN_USER_AGE, userModel.getAge());
        cv.put(COLUMN_USER_HEIGHT, userModel.getHeight());
        cv.put(COLUMN_USER_WEIGHT, userModel.getWeight());
        cv.put(COLUMN_USER_GOAL, userModel.getGoal());
        cv.put(COLUMN_USER_DAYS, userModel.getDays());

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    public boolean doesEmailExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the query to check if the email exists
        String query = "SELECT " + COLUMN_USER_EMAIL + " FROM " + USER_TABLE +
                " WHERE " + COLUMN_USER_EMAIL + " = ?";

        // Perform the query with the email as a parameter
        Cursor cursor = db.rawQuery(query, new String[]{email});

        // Check if a row with the email exists
        boolean emailExists = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return emailExists;
    }
    public UserModel getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        UserModel user = null;

        // Define the columns you want to retrieve
        String[] projection = {
                COLUMN_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_GENDER,
                COLUMN_USER_AGE,
                COLUMN_USER_HEIGHT,
                COLUMN_USER_WEIGHT,
                COLUMN_USER_GOAL,
                COLUMN_USER_DAYS
        };

        // Define the selection criteria (WHERE clause)
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = { email };

        // Perform the query to retrieve the user by email
        Cursor cursor = db.query(
                USER_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            // If a matching user is found, create a UserModel object
            user = new UserModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_GENDER)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_AGE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_HEIGHT)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_WEIGHT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_GOAL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_DAYS))
            );
        }

        cursor.close();
        return user; // Return the UserModel or null if no matching user is found
    }
}