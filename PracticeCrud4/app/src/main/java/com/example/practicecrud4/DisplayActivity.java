package com.example.practicecrud4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    ListView myList;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        myList = (ListView) findViewById(R.id.myList);
        DB = new DBHelper(this);

        List<String> ls = new ArrayList<>();

        Cursor res = DB.getData();
        if(res.getCount()==0)
        {
            Toast.makeText(DisplayActivity.this,"Empty",Toast.LENGTH_LONG).show();
        }
        else {
            while (res.moveToNext()) {
                ls.add(res.getString(0) + " " + res.getString(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4));
            }
            ArrayAdapter<String> adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ls);
            myList.setAdapter(adp);
        }

    }
}