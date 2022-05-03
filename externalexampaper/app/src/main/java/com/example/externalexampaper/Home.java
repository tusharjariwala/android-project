package com.example.externalexampaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
ListView list_views;
DBHelper dbHelper;
Button btnback;
   EditText editid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list_views=findViewById(R.id.list_views);
        btnback=findViewById(R.id.btnback);
        dbHelper=new DBHelper(this);
         List<String> ls=new ArrayList<>();
         List<String> ids=new ArrayList<>();
        Cursor c;
        c=dbHelper.getData();
        if(c.getCount()==0)
        {
            Toast.makeText(Home.this,"empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (c.moveToNext())
            {
                ids.add(c.getString(0));
                ls.add(c.getString(0)+"\n  "+c.getString(1)+" "+c.getString(2)+"  "+c.getString(3)+"  "+c.getString(4)+"  "+c.getString(5));
            }
            ArrayAdapter<String>  adp=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ls);
            list_views.setAdapter(adp);
        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,patient.class);
                intent.putExtra("id","0");
                startActivity(intent);

            }
        });
        list_views.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = ids.get(i);

                Intent intent = new Intent(Home.this,patient.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }
}