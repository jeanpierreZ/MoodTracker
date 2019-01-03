package com.jpz.moodtracker.controllers.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jpz.moodtracker.R;

public class HistoricActivity extends AppCompatActivity {

    private TextView mDayOne;
    private TextView mDayTwo;
    private TextView mDayThree;
    private TextView mDayFour;
    private TextView mDayFive;
    private TextView mDaySix;
    private RelativeLayout mDaySeven;

    private Button mButtonSeven;

    private String mNote;
    private String msaveDate;

    private String hier;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mDayOne = findViewById(R.id.activity_historic_day_one);
        mDayTwo = findViewById(R.id.activity_historic_day_two);
        mDayThree = findViewById(R.id.activity_historic_day_three);
        mDayFour = findViewById(R.id.activity_historic_day_four);
        mDayFive = findViewById(R.id.activity_historic_day_five);
        mDaySix = findViewById(R.id.activity_historic_day_six);
        mDaySeven = findViewById(R.id.activity_historic_day_seven);

        mButtonSeven = findViewById(R.id.activity_historic_seven_btn);

        // Save the comment and print it
        mPreferences = getSharedPreferences("test", MODE_PRIVATE);

        mNote = mPreferences.getString(MainActivity.BUNDLE_STATE_NOTE,null);

        printComment();

        // Save the date and print it
        mPreferences = getSharedPreferences("date", MODE_PRIVATE);

        msaveDate = mPreferences.getString(MainActivity.BUNDLE_STATE_DATE, null);

        // yesterday

        mPreferences = getSharedPreferences("yesterday", MODE_PRIVATE);

        hier = mPreferences.getString(MainActivity.BUNDLE_STATE_YESTERDAY, null);

        printDate();

    }

    private void printComment() {
        mDayFive.setText(mNote);
    }

    private void printDate() {
        mDayTwo.setText(msaveDate);
        mDayThree.setText(hier);
    }
}
