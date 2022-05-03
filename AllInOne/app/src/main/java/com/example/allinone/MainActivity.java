package com.example.allinone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static DBmain dBmain;
    private static EditText edtName , edtPhone , edtAddress;
    RadioGroup radioGroup;
    RadioButton radioMale , radioFemale , radioGender;
    CheckBox checkRead , checkWrite , checkTravel;
    private static Button submit , display , edit;
    private static SQLiteDatabase sqLiteDatabase;
    private static int id = 0 , selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBmain = new DBmain(MainActivity.this );
        findid();
        getData();
        clear();
        editData();
    }

    public void editData() {
        if(getIntent().getBundleExtra("userdata")!=null){
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("id");
            edtName.setText(bundle.getString("name"));
            edtAddress.setText(bundle.getString("address"));
            edtPhone.setText(bundle.getString("phone"));
            radioGroup.check(selectedId);
            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
        }
    }

    public void clear(){
        edtName.setText("");
        edtPhone.setText("");
        edtAddress.setText("");
        radioGroup.clearCheck();
        checkWrite.setChecked(false);
        checkRead.setChecked(false);
        checkTravel.setChecked(false);
    }

    public void getData(){

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues contentValues = new ContentValues();

                selectedId = radioGroup.getCheckedRadioButtonId();
                radioGender = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(MainActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(MainActivity.this,radioGender.getText(), Toast.LENGTH_SHORT).show();
                    contentValues.put("gender",radioGender.getText().toString());
                }

                StringBuilder result=new StringBuilder();
                if(checkRead.isChecked()){
                    result.append(checkRead.getText() + " ");
                }
                if(checkWrite.isChecked()){
                    result.append(checkWrite.getText() + " ");
                }
                if(checkTravel.isChecked()){
                    result.append(checkTravel.getText() + " ");
                }

                contentValues.put("hobby" , result.toString());
                contentValues.put("name" , edtName.getText().toString() );
                contentValues.put("address" , edtAddress.getText().toString());
                contentValues.put("phone" , edtPhone.getText().toString());

                sqLiteDatabase = dBmain.getWritableDatabase();
                Long recid = sqLiteDatabase.insert("stud" , null , contentValues );

                if(recid!=null){
                    Toast.makeText(MainActivity.this , "Data Inserted Successfully ... :) " , Toast.LENGTH_SHORT).show();
                    clear();
                } else{
                    Toast.makeText(MainActivity.this , "Something went Wrong ! Try again :(" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , DisplayData.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues contentValues = new ContentValues();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioGender = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(MainActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,radioGender.getText(), Toast.LENGTH_SHORT).show();
                    contentValues.put("gender",radioGender.getText().toString());
                }

                StringBuilder result=new StringBuilder();
                if(checkRead.isChecked()){
                    result.append(checkRead.getText() + " ");
                }
                if(checkWrite.isChecked()){
                    result.append(checkWrite.getText() + " ");
                }
                if(checkTravel.isChecked()){
                    result.append(checkTravel.getText() + " ");
                }

                contentValues.put("hobby" , result.toString());

                contentValues.put("name" , edtName.getText().toString() );
                contentValues.put("address" , edtAddress.getText().toString());
                contentValues.put("phone" , edtPhone.getText().toString());

                sqLiteDatabase = dBmain.getWritableDatabase();

                long recid = sqLiteDatabase.update("stud" , contentValues , "id = " + id , null);

                if(recid!=-1){
                    Toast.makeText(MainActivity.this , "Data Updated Successfully ... :) " , Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    clear();
                } else {
                    Toast.makeText(MainActivity.this , "Something went Wrong ! Try Again :(" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void findid(){
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        submit = findViewById(R.id.btnSubmit);
        edit = findViewById(R.id.btnEdit);
        display = findViewById(R.id.btnDisplay);
        checkRead = findViewById(R.id.checkRead);
        checkWrite = findViewById(R.id.checkWrite);
        checkTravel = findViewById(R.id.checkTravel);
    }

}