package com.deva.tidyv2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    TimePickerDialog timePicker;
    DatePickerDialog datePicker;
    EditText dueDate;
    EditText dueTime;
    EditText taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Bundle b = getIntent().getExtras();

        Toast.makeText(this, "Task " + Integer.toString(b.getInt("taskNumber")),
                Toast.LENGTH_SHORT).show();

        taskName = (EditText) findViewById(R.id.taskName);
        dueDate = (EditText) findViewById(R.id.dueDate);
        dueTime = (EditText) findViewById(R.id.dueTime);

        dueTime.setInputType(EditorInfo.TYPE_NULL);
        dueTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePicker = new TimePickerDialog(AddTaskActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                dueTime.setText(String.format("%02d", sHour) + ":" + String.format("%02d", sMinute));
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });
        dueDate.setInputType(EditorInfo.TYPE_NULL);
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // time picker dialog
                datePicker = new DatePickerDialog(AddTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int sYear, int sMonth, int sDate) {
                                sMonth++;
                                dueDate.setText(sYear + "-" + sMonth + "-" + sDate);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

    }

    public void onClick(View view){
        Intent i = new Intent();
        Log.d("due date", dueDate.getText().toString());
        Log.d("due time", dueTime.getText().toString());

        String due = dueDate.getText().toString() + ' ' + dueTime.getText().toString() + ":00";

        Log.d("due", due);
        Task newTask = new Task( taskName.getText().toString(), Timestamp.valueOf(due));
        i.putExtra("newTask", newTask);

        setResult(RESULT_OK, i);
        finish();
    }
}
