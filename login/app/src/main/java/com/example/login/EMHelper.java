package com.example.login;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class EMHelper extends SQLiteOpenHelper {

    public EMHelper(@Nullable Context context) {
        super(context, "onlinefood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table siteinfomaster(sid Integer primary key Autoincrement,sname Text,description Text,contact Text,address Text,lastu Text,userid Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists siteinfomaster");

    }

    void insertsiteinfo(Integer sid, String sname, String description, String contact, String address, String lastu, String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sid", sid);
        cv.put("sname", sname);
        cv.put("description", description);
        cv.put("contact", contact);
        cv.put("address", address);
        cv.put("lastu", lastu);
        cv.put("userid", userid);
        db.insert("siteinfomaster", null, cv);
        db.close();
    }

    public boolean updatesiteinfo(Integer sid, String sname, String description, String contact, String address, String lastu, String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sid", sid);
        cv.put("sname", sname);
        cv.put("description", description);
        cv.put("contact", contact);
        cv.put("address", address);
        cv.put("lastu", lastu);
        cv.put("userid", userid);
        Cursor cursor = db.rawQuery("select * from siteinfomaster where sid=?", new String[]{sid.toString()});
        if (cursor.getCount() > 0) {
            long res = db.update("siteinfomaster", cv, "sid=?", new String[]{sid.toString()});
            if (res == 1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean deletesiteinfo(Integer sid) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from siteinfomaster where sid=?", new String[]{sid.toString()});
        if (cursor.getCount() > 0) {
            long res = db.delete("siteinfomaster", "sid=?", new String[]{sid.toString()});
            if (res == 1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return  false;
    }

}
  @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> getdata()
  {
      SQLiteDatabase db=this.getWritableDatabase();
      ContentValues cv=new ContentValues();
      ArrayList<HashMap<String,String>> sitelist=new ArrayList<>();
      Cursor cursor=db.rawQuery("select * from siteinfomaster",null);
      while (cursor.moveToNext())
      {
          HashMap<String,String> siteinfos=new HashMap<>();
          siteinfos.put("sid",cursor.getString(cursor.getColumnIndex("sid")));
          siteinfos.put("sname",cursor.getString(cursor.getColumnIndex("sname")));
          siteinfos.put("description",cursor.getString(cursor.getColumnIndex("description")));
          siteinfos.put("contact",cursor.getString(cursor.getColumnIndex("contact")));
          siteinfos.put("address",cursor.getString(cursor.getColumnIndex("address")));
          siteinfos.put("lastu",cursor.getString(cursor.getColumnIndex("lastu")));
          siteinfos.put("userid",cursor.getString(cursor.getColumnIndex("userid")));
          sitelist.add(siteinfos);
      }
      return sitelist;
  }
}
