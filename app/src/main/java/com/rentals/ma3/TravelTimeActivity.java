package com.rentals.ma3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;


public class TravelTimeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "TravelTimeActivity";
    private Button btnPickTime, btnContinue;
    private EditText dateText;
    private RadioRealButtonGroup group1, group2, group3, group4, group5;
    private RadioRealButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    private String peak, rush, demand, traffic, weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_time);

        hideSoftKeyboard();

        btnPickTime = findViewById(R.id.pick_time);
        btnContinue = findViewById(R.id.submit_conditions);
        dateText = findViewById(R.id.date_and_time);
        group1 = findViewById(R.id.group_1);

        button1 =findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);

        group2 = findViewById(R.id.group_2);

        button3 =findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);


        group3 = findViewById(R.id.group_3);

        button5 =findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);


        group4 = findViewById(R.id.group_4);

        button7 =findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);


        group5 = findViewById(R.id.group_5);

        button9 =findViewById(R.id.button_9);
        button10 = findViewById(R.id.button_10);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateAndTime = simpleDateFormat.format(new Date());
        dateText.setText(currentDateAndTime);



        btnPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDatePickerDialog();
            }
        });

        group1.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {

                peak = btnPickTime.getText().toString();
                Log.e("peak", button.getText().toString());
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TravelTimeActivity.this, ConditionsActivity.class);
                startActivity(intent);
            }
        });


    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String date = year + "-" + (month+1) + "-" + dayOfMonth + " " + currentTime;
        dateText.setText(date);
    }


    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
