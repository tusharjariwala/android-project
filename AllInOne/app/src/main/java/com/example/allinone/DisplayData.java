package com.example.allinone;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayData extends AppCompatActivity {

    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String []name ;
    String []phone;
    String []address;
    String []gender;
    String []hobby;
    int[]id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dBmain = new DBmain(DisplayData.this);
        findid();
        dis();

    }

    private void dis(){

        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select *from stud" , null);

        if(cursor.getCount()>0){

            id = new int[cursor.getCount()];
            name = new String[cursor.getCount()];
            address = new String[cursor.getCount()];
            phone = new String[cursor.getCount()];
            gender = new String[cursor.getCount()];
            hobby = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()){
                id[i] = cursor.getInt(0);
                name[i] = cursor.getString(1);
                address[i] = cursor.getString(2);
                phone[i] = cursor.getString(3);
                gender[i] = cursor.getString(4);
                hobby[i] = cursor.getString(5);
                i++;
            }

            Custom adapter = new Custom();
            listView.setAdapter(adapter);
        }
    }

    private void findid(){
        listView = findViewById(R.id.listview);
    }

    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position  ) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView txt_Name , txt_Gender , txt_Phone , txt_Address , txt_Hobby;
            ImageView edit , delete;

            convertView = LayoutInflater.from(DisplayData.this).inflate(R.layout.singledata , parent , false);
            txt_Name = convertView.findViewById(R.id.txt_Name);
            txt_Address = convertView.findViewById(R.id.txt_Address);
            txt_Gender = convertView.findViewById(R.id.txt_Gender);
            txt_Phone = convertView.findViewById(R.id.txt_Phone);
            txt_Hobby = convertView.findViewById(R.id.txt_Hobby);
            edit = convertView.findViewById(R.id.editdata);
            delete = convertView.findViewById(R.id.deletedata);

            txt_Name.setText(name[position]);
            txt_Address.setText(address[position]);
            txt_Gender.setText(gender[position]);
            txt_Phone.setText(phone[position]);
            txt_Hobby.setText(hobby[position]);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("id" , id[position]);
                    bundle.putString("name" , name[position]);
                    bundle.putString("address",address[position]);
                    bundle.putString("gender",gender[position]);
                    bundle.putString("phone",phone[position]);
                    bundle.putString("hobby",hobby[position]);

                    Intent intent = new Intent(DisplayData.this , MainActivity.class);
                    intent.putExtra("userdata" , bundle);
                    startActivity(intent);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    sqLiteDatabase = dBmain.getReadableDatabase();
                    long recd = sqLiteDatabase.delete("stud" , "id = "+id[position] , null);

                    if(recd!=-1){
                        Toast.makeText(DisplayData.this , "Record deleted successfully ... :)" , Toast.LENGTH_SHORT).show();
                        dis();
                    }
                }
            });

            return convertView;
        }
    }
}