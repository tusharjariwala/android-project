package com.example.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Button btnback;
    ListView myList;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnback=findViewById(R.id.btnback);
        myList = (ListView) findViewById(R.id.myList);
        DB = new DbHelper(this);

        List<String> ls=new ArrayList<>();

        Cursor c;
        c = DB.getData();
        if(c.getCount()==0)
        {
            Toast.makeText(MainActivity2.this,"Empty",Toast.LENGTH_LONG).show();
        }
        else
        {
            while(c.moveToNext())
            {
                ls.add("ID: "+c.getString(0)+" "+c.getString(1)+", "+"\n"+c.getString(2)+" "+"\n"+c.getString(3)+" "+"\n"+c.getString(4));
            }
            ArrayAdapter<String> adp=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ls);
            myList.setAdapter(adp);
        }

        myList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity2.this,showDetail.class);
                intent.putExtra("Data",ls.get(i));
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });
    }
}