package com.example.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context)
    {
        super(context,"student.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table student" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,name Text,address Text,city Text,Pincode INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists student");
    }

    public Boolean insertData(String name,String address,String city,int pincode)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("city",city);
        contentValues.put("pincode",pincode);
        long result = DB.insert("student",null,contentValues);
        if(result>0)
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
        Cursor cursor = DB.rawQuery("select * from student",null);
        return cursor;
    }

    public Boolean updateData(int id,String name,String address,String city,int pincode)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("city",city);
        contentValues.put("pincode",pincode);

        Cursor cursor = DB.rawQuery("select * from student where id = ?",new String[] {id + ""});
        if(cursor.getCount()>0)
        {
            long result = DB.update("student",contentValues,"id=?",new String[] {id+""});
            if(result>0)
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
        Cursor cursor = DB.rawQuery("select * from student where id=?",new String[] {id+""});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("student","id=?",new String[] {id+""});
            if(result>0)
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
    public Cursor searchData(int id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from student where id=?",new String[] {id+""});
        return cursor;
    }
}

