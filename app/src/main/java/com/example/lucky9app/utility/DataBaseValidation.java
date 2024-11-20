package com.example.lucky9app.utility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lucky9app.dbase.DBHelper;

public class DataBaseValidation {
    private final DBHelper dbHelper;
    public DataBaseValidation(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean usernameExists(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_USERS + " WHERE " + DBHelper.COLUMN_USERNAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }



    public boolean accountExists(String username){
        return usernameExists(username);
    }


}
