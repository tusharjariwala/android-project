package com.example.externalexampaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class patient extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton,radiocholera,radioFever;
    CheckBox checknh1 , checknh20 , checknh30;

Button btnadd,btndisplay,btnupdate,btndelete,btnsearch  ;
EditText editname,editmedication,editarrivaldate,editcost,editid,editdis;
DBHelper dbHelper;
String patientId ="0" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        btnadd=findViewById(R.id.btnadd);
//        editid=findViewById(R.id.editid);

        btndisplay=findViewById(R.id.btndisplay);
        btnupdate=findViewById(R.id.btnupdate);
        btndelete=findViewById(R.id.btndelete);
//        btnsearch=findViewById(R.id.btnsearch);
        editname=findViewById(R.id.editname);

        radioGroup=findViewById(R.id.radioGo);

        checknh1=findViewById(R.id.checknh1);
        checknh20=findViewById(R.id.checknh20);
        checknh30=findViewById(R.id.checknh30);

        editarrivaldate=findViewById(R.id.editarrivaldate);
        editcost=findViewById(R.id.editcost);
        dbHelper=new DBHelper(this);
        Intent intent =getIntent();
        patientId = intent.getStringExtra("id");
        if(!patientId.equalsIgnoreCase("0")){
            radiocholera=findViewById(R.id.radiocholera);
            radioFever=findViewById(R.id.radioFever);

            int id=Integer.parseInt(patientId);
            Cursor res= dbHelper.searchData(id);
            if(res.getCount()==0)
            {
                Toast.makeText(patient.this,"Empty",Toast.LENGTH_SHORT).show();
            }
            while(res.moveToNext())
            {
                editname.setText(res.getString(1));

                if(res.getString(2).equalsIgnoreCase("cholera")) {
                    radiocholera.setChecked(true);
                }else if(res.getString(2).equalsIgnoreCase("fever")){
                    radioFever.setChecked(true);
                }

                if(res.getString(3).contains("nh1")) {
                    checknh1.setChecked(true);
                }else if(res.getString(3).contains("nh20")){
                    checknh20.setChecked(true);
                }
                else if(res.getString(3).contains("nh30")){
                    checknh30.setChecked(true);
                }
                // editmedication.setText(res.getString(3));
                editarrivaldate.setText(res.getString(4));
                editcost.setText(res.getString(5));
            }
        }
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

                String disease=radioButton.getText().toString();

                StringBuilder result=new StringBuilder();
                if(checknh1.isChecked()){
                    result.append(checknh1.getText() + " ");
                }
                if(checknh20.isChecked()){
                    result.append(checknh20.getText() + " ");
                }
                if(checknh30.isChecked()){
                    result.append(checknh30.getText() + " ");
                }

                String name=editname.getText().toString();

                String medication=result.toString();
                String arrivaldate=editarrivaldate.getText().toString();
                String cost=editcost.getText().toString();

                boolean res=dbHelper.insertpatient(name,disease,medication,arrivaldate,cost);
                if(res)
                {
                    Toast.makeText(patient.this,"insert",Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(patient.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(patient.this,"Not Add",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

                String disease=radioButton.getText().toString();
                StringBuilder result=new StringBuilder();
                if(checknh1.isChecked()){
                    result.append(checknh1.getText() + " ");
                }
                if(checknh20.isChecked()){
                    result.append(checknh20.getText() + " ");
                }
                if(checknh30.isChecked()){
                    result.append(checknh30.getText() + " ");
                }
                int id=Integer.parseInt(patientId);
                String name=editname.getText().toString();

                String medication=result.toString();
                String arrivaldate=editarrivaldate.getText().toString();
                String cost=editcost.getText().toString();

                boolean res=dbHelper.updatepatient(id,name,disease,medication,arrivaldate,cost);
                if(res)
                {
                    Toast.makeText(patient.this,"update",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(patient.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(patient.this,"Not update",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=Integer.parseInt(patientId);


                boolean res=dbHelper.deleteData(id);
                if(res)
                {
                    Toast.makeText(patient.this,"Delete",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(patient.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(patient.this,"Not Delete",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btndisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(patient.this,Home.class);
                startActivity(intent);
            }
        });
    }


}