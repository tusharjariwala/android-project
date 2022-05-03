package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class details extends AppCompatActivity {
Intent intent;
Button btnback;
ListView list_site;
EMHelper em;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        btnback=findViewById(R.id.btnback);
        list_site=findViewById(R.id.list_site);
       em=new EMHelper(this);
        ArrayList<HashMap<String,String>> sitelits=em.getdata();

        ListAdapter adapter=new SimpleAdapter(details.this,sitelits,R.layout.list_row,
                new String[]{"sid","sname","description","contact","address","lastu","userid"},new int[]{R.id.textid,
        R.id.textname,R.id.textdes,R.id.textcon,R.id.textadd,R.id.textlastup,R.id.textuser});
        list_site.setAdapter(adapter);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(details.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}