package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
Button btnlogin;
EditText editusername,editpassword;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editusername=findViewById(R.id.editusername);
        editpassword=findViewById(R.id.editpassword);
        btnlogin=findViewById(R.id.btnlogin);
        DB =new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=editusername.getText().toString();
                String password=editpassword.getText().toString();
              if(user.equals("")||password.equals(""))
              {
                  Toast.makeText(LoginActivity.this, "Please enter all field", Toast.LENGTH_SHORT).show();
              }
              else
              {
                 Boolean checkuserpass=DB.checkusernamepassword(user,password);
                 if(checkuserpass==true)
                 {
                     Toast.makeText(LoginActivity.this,"sign in  successfully",Toast.LENGTH_SHORT).show();
                     Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                     startActivity(intent);
                 }
                 else
                 {
                     Toast.makeText(LoginActivity.this,"Invalid ",Toast.LENGTH_SHORT).show();
                 }
              }
            }
        });
    }
}