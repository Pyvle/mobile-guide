package com.example.ebatpricols;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
            COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
            COLUMN_PASSWORD + " TEXT," +
            COLUMN_ROLE + " TEXT)";

    public  DataBaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("ALTER TABLE " + TABLE_USERS + " ADD COLUMN role TEXT DEFAULT 'читатель'");
    }

    public boolean addUser(String username, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ROLE, role);

        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_USERNAME}, COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_PASSWORD}, COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);

        if (cursor.moveToFirst()) {
            String storedPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            cursor.close();
            return password.equals(storedPassword);
        }

        cursor.close();
        return false;
    }

    public String getUserRole(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ROLE}, COLUMN_USERNAME + " = ?", new String[]{username}, null,null,null);

        if(cursor.moveToFirst()) {
            String role = cursor.getString(cursor.getColumnIndex(COLUMN_ROLE));
            cursor.close();
            return role;
        }

        cursor.close();
        return null;
    }
}
