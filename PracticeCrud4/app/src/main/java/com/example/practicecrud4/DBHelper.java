package com.example.practicecrud4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context)
    {
        super(context,"practice1.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table prac1(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,address TEXT,city TEXT,pincode INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists prac1");
    }

    public boolean insertData(String name,String address,String city,int pincode)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("city",city);
        contentValues.put("pincode",pincode);

        long result =  DB.insert("prac1",null,contentValues);
        if(result==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from prac1",null);
        return cursor;
    }

    public Cursor getDataById(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from prac1 where id=?",new String[] {id+""});
        return cursor;
    }

    public boolean updateData(int id,String name,String address,String city,int pincode)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("city",city);
        contentValues.put("pincode",pincode);

        Cursor cursor = DB.rawQuery("select * from prac1 where id=?",new String[] {id+""});
        if(cursor.getCount()>0)
        {
            long result = DB.update("prac1",contentValues,"id=?",new String[] {id+""});
            if(result==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }

    public boolean deleteData(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from prac1 where id=?",new String[] {id+""});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("prac1","id=?",new String[] {id+""});
            if(result==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

}
