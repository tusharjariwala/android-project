package com.example.externalexampaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btnlogin,btndoctor;
EditText editemailid,editpassword;
DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndoctor=findViewById(R.id.btndoctor);
        editemailid=findViewById(R.id.editemailid);
        editpassword=findViewById(R.id.editpassword);
        btnlogin=findViewById(R.id.btnlogin);
        db=new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String demail=editemailid.getText().toString();
                String dpassword=editpassword.getText().toString();
                if(demail.equals("")||dpassword.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Enter the value", Toast.LENGTH_SHORT).show();
                }
                else
                {
                     boolean checkemailpass=db.checkemailandpassword(demail,dpassword);
                       if(checkemailpass==true)
                       {
                           Toast.makeText(MainActivity.this,"Sign in successfull",Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(MainActivity.this,Home.class);
                           startActivity(intent);

                       }
                       else
                       {
                           Toast.makeText(MainActivity.this, "Invalid ", Toast.LENGTH_SHORT).show();
                       }

                }

            }
        });
        btndoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,doctor.class);
                startActivity(intent);
            }
        });
    }
}