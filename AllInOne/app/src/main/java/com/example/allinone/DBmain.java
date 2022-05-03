package com.example.allinone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {

    public DBmain(@Nullable Context context) {
        super(context, "studdb" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table stud(id integer primary key , name text , address text , phone text , gender text ,  hobby text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists stud";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


}

