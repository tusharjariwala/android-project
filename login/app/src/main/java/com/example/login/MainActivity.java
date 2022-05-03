package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btnsignup,btnsignin;
EditText editusername,editpassword,editrepassword;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editusername=findViewById(R.id.editusername);
        editpassword=findViewById(R.id.editpassword);
        editrepassword=findViewById(R.id.editrepassword);
        btnsignup=findViewById(R.id.btnsignup);
        btnsignin=findViewById(R.id.btnsignin);
        DB =new DBHelper(this);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String user=editusername.getText().toString();
            String password=editpassword.getText().toString();
            String retypepassword=editrepassword.getText().toString();
            if(user.equals("")||password.equals("")||retypepassword.equals(""))
            {
                Toast.makeText(MainActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(password.equals(retypepassword))
                {
                    Boolean checkuser=DB.checkusername(user);
                    if(checkuser==false)
                    {
                        Boolean insert=DB.insertData(user,password);
                        if (insert==true)
                        {
                            Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(MainActivity.this,"Registered Failed",Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        Toast.makeText(MainActivity.this,"user already exists! please sign",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Password not matching",Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}