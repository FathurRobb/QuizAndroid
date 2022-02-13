package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="db_quiz";

    private static final String TABLE_USERS="tb_users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_TELEPON = "telepon";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHandler(@Nullable Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_MAHASISWA="CREATE TABLE "+TABLE_USERS+"("
                +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_NAMA+" TEXT, "
                +COLUMN_TELEPON+" TEXT, "
                +COLUMN_EMAIL+" TEXT, "
                +COLUMN_PASSWORD+" TEXT"+")";
        sqLiteDatabase.execSQL(CREATE_TABLE_MAHASISWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public void insertUsers(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA,users.getNama());
        values.put(COLUMN_TELEPON,users.getTelepon());
        values.put(COLUMN_EMAIL,users.getEmail());
        values.put(COLUMN_PASSWORD,users.getPassword());
        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{COLUMN_ID, COLUMN_NAMA, COLUMN_TELEPON, COLUMN_EMAIL, COLUMN_PASSWORD},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = { COLUMN_ID} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_EMAIL + "=?" + " and " + COLUMN_PASSWORD + "=?";
        String[] selectionArgs = { email, password };
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();


        if (count>0)
            return true;
        else
            return false;
    }

}
