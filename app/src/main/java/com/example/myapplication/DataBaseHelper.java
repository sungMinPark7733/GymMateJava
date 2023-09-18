package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
//  User table
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
    private static final String CREATE_USER_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " +
                    USER_TABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT UNIQUE, " +
                    COLUMN_USER_NAME + " TEXT, " +
                    COLUMN_USER_GENDER + " TEXT, " +
                    COLUMN_USER_AGE + " INT, " +
                    COLUMN_USER_HEIGHT + " INT, " +
                    COLUMN_USER_WEIGHT + " REAL, " +
                    COLUMN_USER_GOAL + " TEXT, " +
                    COLUMN_USER_DAYS + " TEXT)";
//  Food table
    public static final String FOOD_TABLE = "FOOD_TABLE";
    public static final String COLUMN_FOOD_ID = "ID";
    public static final String COLUMN_FOOD_NAME = "FOOD_NAME";
    public static final String COLUMN_FOOD_CALORIES = "CALORIES";
    public static final String COLUMN_FOOD_PORTION = "PORTION";
    public static final String COLUMN_FOOD_PROTEIN = "PROTEIN";
    public static final String COLUMN_FOOD_CARBS = "CARBS";
    public static final String COLUMN_FOOD_FAT = "FAT";
    private static final String CREATE_FOOD_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS " +
                    FOOD_TABLE + " (" +
                    COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FOOD_NAME + " TEXT, " +
                    COLUMN_FOOD_CALORIES + " INT, " +
                    COLUMN_FOOD_PORTION + " TEXT, " +
                    COLUMN_FOOD_PROTEIN + " REAL, " +
                    COLUMN_FOOD_CARBS + " REAL, " +
                    COLUMN_FOOD_FAT + " REAL)";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "gymmate.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE_QUERY);
        db.execSQL(CREATE_FOOD_TABLE_QUERY);
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

    public boolean updateUserWeight(String email, int newWeight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_WEIGHT, newWeight);

        // Define the WHERE clause to update the user with the matching email
        String whereClause = COLUMN_USER_EMAIL + " = ?";
        String[] whereArgs = { email };

        int rowsUpdated = db.update(USER_TABLE, values, whereClause, whereArgs);
        return rowsUpdated > 0;
    }

    public List<FoodModel> getEveryfood() {
        List<FoodModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + FOOD_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int foodID = cursor.getInt(0);
                String foodName = cursor.getString(1);
                int foodCalories = cursor.getInt(2);
                String foodPortion = cursor.getString(3);
                float foodProtein = cursor.getFloat(4);
                float foodCarbs = cursor.getFloat(5);
                float foodFat = cursor.getFloat(6);

                FoodModel newFoodModel = new FoodModel(foodID, foodName, foodCalories, foodPortion, foodProtein, foodCarbs, foodFat);
                returnList.add(newFoodModel);

            }

            while (cursor.moveToNext());
        } else {
            // Do nothing
        }

        cursor.close();
        db.close();
        return returnList;
    }
}