package com.example.lucky9app.dbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDAO {
    private final DBHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean addUser(String username, String email, String password, String gender, int age, String contactNumber, String address) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_USERNAME, username);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_PASSWORD, password);
        values.put(DBHelper.COLUMN_GENDER, gender);
        values.put(DBHelper.COLUMN_AGE, age);
        values.put(DBHelper.COLUMN_CONTACT_NUMBER, contactNumber);
        values.put(DBHelper.COLUMN_ADDRESS, address);

        long result = db.insert(DBHelper.TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }



    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_USERS +
                " WHERE " + DBHelper.COLUMN_USERNAME + "=? AND " +
                DBHelper.COLUMN_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }

    public int getUserIdByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int userId = -1;

        String query = "SELECT id FROM users WHERE username =?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userId = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
            }
            cursor.close();
        }

        db.close();
        return userId;
    }
}
