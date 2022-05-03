package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
Button btnadd,btnupdate,btndisplay,btndelete;
EditText editid,editname,editdesc,editcon,editadd,editlastup,edituser;
Intent intent;
EMHelper em;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnadd=findViewById(R.id.btnadd);
        btnupdate=findViewById(R.id.btnupdate);
        btndisplay=findViewById(R.id.btndisplay);
        btndelete=findViewById(R.id.btndelete);
        editid=findViewById(R.id.editid);
        editname=findViewById(R.id.editname);
        editdesc=findViewById(R.id.editdesc);
        editcon=findViewById(R.id.editcon);
        editadd=findViewById(R.id.editadd);
        editlastup=findViewById(R.id.editlastup);
        edituser=findViewById(R.id.edituser);


        em=new EMHelper(this);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(editid.getText().toString());
                String name=editname.getText().toString();
                String descr=editdesc.getText().toString();
                String contact=editcon.getText().toString();
                String address=editadd.getText().toString();
                String lastup=editlastup.getText().toString();
                String user=edituser.getText().toString();
                em.insertsiteinfo(id,name,descr,contact,address,lastup,user);
             intent=new Intent(HomeActivity.this,details.class);
             startActivity(intent);
             Toast.makeText(HomeActivity.this,"insert",Toast.LENGTH_SHORT).show();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(editid.getText().toString());
                String name=editname.getText().toString();
                String descr=editdesc.getText().toString();
                String contact=editcon.getText().toString();
                String address=editadd.getText().toString();
                String lastup=editlastup.getText().toString();
                String user=edituser.getText().toString();
               boolean res= em.updatesiteinfo(id,name,descr,contact,address,lastup,user);
               if(res==true)
               {
                   Toast.makeText(HomeActivity.this,"Update",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(HomeActivity.this,"Not Udpate",Toast.LENGTH_SHORT).show();
               }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(editid.getText().toString());

                boolean res= em.deletesiteinfo(id);
                if(res==true)
                {
                    Toast.makeText(HomeActivity.this,"Delete",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(HomeActivity.this,"Not Delete",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(HomeActivity.this,details.class);
                startActivity(intent);
            }
        });
    }
}