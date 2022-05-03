package com.example.externalexampaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class doctor extends AppCompatActivity {
    Button btnRegister,btnexits;
    EditText editemailid,editpassword;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        btnexits=findViewById(R.id.btnexits);
        btnRegister=findViewById(R.id.btnRegister);
        editemailid=findViewById(R.id.editemailid);
        editpassword=findViewById(R.id.editpassword);
        DB=new DBHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=  editemailid.getText().toString();
                String password=  editpassword.getText().toString();
               if(email.equals("")||password.equals(""))
               {
                   Toast.makeText(doctor.this,"please enter the details",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   boolean checkuser=DB.checkemail(email);
                   if(checkuser==false) {
                       boolean checkdata = DB.insertData(email, password);
                       if (checkdata == true) {
                           Toast.makeText(doctor.this,"Successfull  Register",Toast.LENGTH_SHORT).show();

                           Intent intent=new Intent(doctor.this,Home.class);
                                startActivity(intent);
                       }
                       else
                       {
                           Toast.makeText(doctor.this,"please enter the details",Toast.LENGTH_SHORT).show();

                       }
                   }else
                   {
                       Toast.makeText(doctor.this,"Doctor exists",Toast.LENGTH_SHORT).show();

                   }
               }

            }
        });
        btnexits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(doctor.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}