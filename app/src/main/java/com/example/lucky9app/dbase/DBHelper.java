    package com.example.lucky9app.dbase;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "UserDetailsDatabase";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_USERS = "Users";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_CONTACT_NUMBER = "contact_number";
        public static final String COLUMN_ADDRESS = "address";

        public static final String COLUMN_IMAGE = "profile_image_uri";
        private static final String CREATE_TABLE_USERS =
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT NOT NULL, " +
                        "email TEXT NOT NULL, " +
                        "password TEXT NOT NULL, " +
                        "gender TEXT NOT NULL, " +
                        "age INTEGER NOT NULL, " +
                        "contact_number TEXT, " +
                        "address TEXT" +
                        ");";

        private static final String CREATE_PICTURE_TABLE = "CREATE TABLE user_profile_pictures (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER NOT NULL, " +
                "profile_picture BLOB, " +
                "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
                ");";

        public DBHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_USERS);
            db.execSQL(CREATE_PICTURE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int preVer, int newVer) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);

        }


    }
