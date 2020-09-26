package com.truongtuananh.truongtuananh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "Login.db",null,1);
    }

    public Database(@Nullable Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, name,factory,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text PRIMARY KEY, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    public boolean insert(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("Password",password);
        long res = db.insert("user",null,contentValues);
        if (res==-1) return false;
        else return true;
    }

    public boolean checkusername (String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?",new String[]{username});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public Boolean usernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select  * from user where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
