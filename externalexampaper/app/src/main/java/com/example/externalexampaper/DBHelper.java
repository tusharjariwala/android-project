package com.example.externalexampaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "logindoctor", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table doctortbl(email TEXT primary key,password TEXT)");
        db.execSQL("create table patienttbl(id Integer primary key autoincrement,name TEXT,disease TEXT,medication TEXT, datearrival TEXT, cost TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists doctortbl");
        db.execSQL("drop table if exists patienttbl");

    }
    public Boolean insertData(String email,String password)
    {
         SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",password);


        long res=db.insert("doctortbl",null,cv);
       if(res==-1)
       {
        return false;
       }
       else
       {
           return true;
       }

    }
    public boolean checkemail(String email)
    {
          SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from doctortbl where email=?",new String[]{email});
         if(cursor.getCount()>0)
         {

             return true;
         }
         else
         {
             return false;
         }
    }
    public boolean checkemailandpassword(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from doctortbl where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0)
        {

            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean insertpatient(String name,String disease,String medication,String datearrival , String cost)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("disease",disease);
        cv.put("medication",medication);
        cv.put("datearrival",datearrival);
        cv.put("cost",cost);
        long res=db.insert("patienttbl",null,cv);
        if(res>0)
            return true;
        else
            return false;

    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from patienttbl",null);
        return cursor;
    }
    public boolean updatepatient(int id,String name,String disease,String medication,String datearrival , String cost)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("disease",disease);
        cv.put("medication",medication);
        cv.put("datearrival",datearrival);
        cv.put("cost",cost);
        Cursor  cursor=db.rawQuery("select * from patienttbl where id=?",new String[]{id+" "});
        if(cursor.getCount()>0) {
            long res = db.update("patienttbl", cv,"id=?",new String[]{id+" "});
            if (res > 0)
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }
    public boolean deleteData(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor  cursor=db.rawQuery("select * from patienttbl where id=?",new String[]{id+" "});
        if(cursor.getCount()>0) {
            long res = db.delete("patienttbl","id=?",new String[]{id+" "});
            if (res > 0)
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }
    public Cursor searchData(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from patienttbl where id=?",new String[]{id+" "});
        return cursor;
    }
}
