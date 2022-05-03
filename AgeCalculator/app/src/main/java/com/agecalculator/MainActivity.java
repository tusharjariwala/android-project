package com.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView yearTextView, monthTextView, weekTextView, dayTextView, hourTextView, minuteTextView, secondTextView, milliSecondTextView;
    Button calculateButton;
    EditText ediTextBirthDate;
    Calendar currentDate = Calendar.getInstance();
    Calendar birthDate = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateSetListener;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ediTextBirthDate = findViewById(R.id.ediTextBirthDate);
        calculateButton = findViewById(R.id.calculateButton);
        yearTextView = findViewById(R.id.yearTextView);
        monthTextView = findViewById(R.id.monthTextView);
        weekTextView = findViewById(R.id.weekTextView);
        dayTextView = findViewById(R.id.dayTextView);
        hourTextView = findViewById(R.id.hourTextView);
        minuteTextView = findViewById(R.id.minuteTextView);
        secondTextView = findViewById(R.id.secondTextView);
        milliSecondTextView = findViewById(R.id.milliSecondTextView);


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                birthDate.set(Calendar.YEAR, i);
                birthDate.set(Calendar.MONTH, i1);
                birthDate.set(Calendar.DAY_OF_MONTH, i2);
                ediTextBirthDate.setText(i2 + "-" + i1 + "-" + i);
            }
        };

        ediTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        Toast.makeText(MainActivity.this, hour + ":" + minute, Toast.LENGTH_LONG).show();
                    }
                }, hour, minute, false);
                timePickerDialog.show();

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, birthDate.get(Calendar.YEAR), birthDate.get(Calendar.MONTH), birthDate.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("Do you want to exit application?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        calculateAge();
                    }
                });
                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alertDialog.show();


            }
        });
    }

    public void calculateAge() {
        long diff = currentDate.getTimeInMillis() - birthDate.getTimeInMillis();

        int day = (int) TimeUnit.MILLISECONDS.toDays(diff);

        int year = day / 365;

        int month = (int) Math.round(year * 12.5);

        int week = (int) (day / 7);

        int hours = day * 24;
        int minutes = hours * 60;
        int seconds = minutes * 60;
        int milliseconds = seconds * 1000;

        yearTextView.setText("Year :" + year);
        monthTextView.setText("Month :" + month);
        weekTextView.setText("Week :" + week);
        dayTextView.setText("Day :" + day);
        hourTextView.setText("Hours :" + hours);
        minuteTextView.setText("Minutes :" + minutes);
        secondTextView.setText("Seconds :" + seconds);
        milliSecondTextView.setText("MilliSeconds :" + milliseconds);
    }
}