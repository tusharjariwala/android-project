package com.example.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtId,txtName,txtAddress,txtCity,txtPincode;
    Button addBtn,displayBtn,searchBtn,updateBtn,deleteBtn,displayBtnNext,reset;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtName);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtCity = (EditText) findViewById(R.id.txtCity);
        txtPincode = (EditText) findViewById(R.id.txtPincode);

        addBtn = (Button) findViewById(R.id.addBtn);
        displayBtn = (Button) findViewById(R.id.displayBtn);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        displayBtnNext = (Button) findViewById(R.id.displayBtnNext);
        reset = (Button) findViewById(R.id.reset);
        DB = new DbHelper(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                int pincode = Integer.parseInt(txtPincode.getText().toString());

                boolean result = DB.insertData(name,address,city,pincode);
                if(result)
                {
                    Toast.makeText(MainActivity.this,"Insert",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Insert",Toast.LENGTH_LONG).show();
                }
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtId.getText().toString());
                Cursor res = DB.searchData(id);
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_LONG).show();
                }
                while(res.moveToNext())
                {
                    txtName.setText(res.getString(1));
                    txtAddress.setText(res.getString(2));
                    txtCity.setText(res.getString(3));
                    txtPincode.setText(res.getString(4));
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtId.getText().toString());
                String name = txtName.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                int pincode = Integer.parseInt(txtPincode.getText().toString());

                boolean result = DB.updateData(id,name,address,city,pincode);
                if(result)
                {
                    Toast.makeText(MainActivity.this,"Update",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Update",Toast.LENGTH_LONG).show();
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtId.getText().toString());
                boolean result = DB.deleteData(id);
                if(result)
                {
                    Toast.makeText(MainActivity.this,"Delete",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Delete",Toast.LENGTH_LONG).show();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtCity.setText("");
                txtPincode.setText("");
            }
        });



    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);

    }
}