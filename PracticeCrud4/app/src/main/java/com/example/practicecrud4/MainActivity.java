package com.example.practicecrud4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtId,txtName,txtAddress,txtCity,txtPincode;
    Button addBtn,displayBtn,searchBtn,updateBtn,deleteBtn,displayNextBtn;
    DBHelper DB;


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
        displayNextBtn = (Button) findViewById(R.id.displayNextBtn);

        DB = new DBHelper(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String address = txtAddress.getText().toString();
                String city = txtCity.getText().toString();
                int pincode = Integer.parseInt(txtPincode.getText().toString());

                Boolean result = DB.insertData(name,address,city,pincode);
                if(result==true)
                {
                    Toast.makeText(MainActivity.this,"Insert",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Insert",Toast.LENGTH_LONG).show();
                }
            }
        });

        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    StringBuilder buffer = new StringBuilder();
                    while(res.moveToNext())
                    {
                        buffer.append("Id : "+res.getString(0)+"\n\n");
                        buffer.append("Name : "+res.getString(1)+"\n\n");
                        buffer.append("Address : "+res.getString(2)+"\n\n");
                        buffer.append("City : "+res.getString(3)+"\n\n");
                        buffer.append("Pincode : "+res.getString(4)+"\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setMessage(buffer.toString());
                    builder.setTitle("Employee");
                    builder.show();
                }
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(txtId.getText().toString());
                Cursor res = DB.getDataById(id);
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    while(res.moveToNext())
                    {
                        txtName.setText(res.getString(1));
                        txtAddress.setText(res.getString(2));
                        txtCity.setText(res.getString(3));
                        txtPincode.setText(res.getString(4));
                    }
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

                Boolean result = DB.updateData(id,name,address,city,pincode);
                if(result==true)
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
                Boolean result = DB.deleteData(id);
                if(result==true)
                {
                    Toast.makeText(MainActivity.this,"Delete",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not Delete",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}