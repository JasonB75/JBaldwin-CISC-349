package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final int MOODMAX = 10;
    private final int MOODMIN = 1;
    private EditText dateEditText, timeEditText;
    private NumberPicker moodPicker;
    private Button nextButton;
    private Context context;

    //Variables for the date picker and edittext
    private int year;
    private int day;
    private int month;

    //VAriables for the timepicker and exittext
    private int hour;
    private int minute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateEditText = findViewById(R.id.idEdtDate);
        timeEditText = findViewById(R.id.idEdtTime);
        moodPicker = findViewById(R.id.mood_picker);
        nextButton = findViewById(R.id.next_button);

        moodPicker.setMaxValue(MOODMAX);
        moodPicker.setMinValue(MOODMIN);

        context = this;

        // on below line we are getting
        // the instance of our calendar.
        final Calendar calendar = Calendar.getInstance();

        //Getting day, month, and year to set default value
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        String dateoutput = (day + "-" + (month + 1) + "-" + year);
        dateEditText.setText(dateoutput);

        //Getting hour and minute to set defult values
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        timeEditText.setText( hour + ":" + minute);

        //On click listner for the date edit text, allows it to be selectable and creates a popout to select
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // on below line we are getting
                // our day, month and year.
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                String dateoutput = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                dateEditText.setText(dateoutput);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        //When the time edit text is clicked, make a popout to select the time
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeEditText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        //When the next button is selected, go to the health collection activity
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intend to new health collection
                int tempMood = moodPicker.getValue();
                String tempDate = dateEditText.getText().toString();
                String tempTime = timeEditText.getText().toString();
                Intent i = HealthCollectionActivity.newIntent(context, tempDate, tempTime, tempMood);

                // at last we are starting our activity.
                context.startActivity(i);
            }
        });
    }
}
