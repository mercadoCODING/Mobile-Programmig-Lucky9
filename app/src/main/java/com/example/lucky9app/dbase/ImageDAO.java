package com.example.lucky9app.dbase;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ImageDAO {
    private final DBHelper dbHelper;
    public ImageDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addProfilePicture(int userId, byte[] imageBytes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("profile_picture", imageBytes);

        long result = db.insert("user_profile_pictures", null, values);
        db.close();

    }

    public byte[] getProfilePicture(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        byte[] image = null;

        Cursor cursor = db.rawQuery(
                "SELECT profile_picture FROM user_profile_pictures WHERE user_id = ?",
                new String[]{String.valueOf(userId)}
        );

        if (cursor != null && cursor.moveToFirst()) {
            image = cursor.getBlob(0);
            cursor.close();
        }

        return image;
    }


}
