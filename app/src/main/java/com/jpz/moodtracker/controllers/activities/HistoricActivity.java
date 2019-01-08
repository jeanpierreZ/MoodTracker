package com.jpz.moodtracker.controllers.activities;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    private int mMood = 0;

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

        mButtonSeven.setEnabled(true);


        // Change width Layout

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        try {
            display.getRealSize(size);
        } catch (NoSuchMethodError err) {
            display.getSize(size);
        }
        int width = size.x;
        int height = size.y;

        LinearLayout.LayoutParams lpSad = new LinearLayout.LayoutParams(width/5,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        LinearLayout.LayoutParams lpDisappointed = new LinearLayout.LayoutParams((width/5)*2,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        LinearLayout.LayoutParams lpNormal = new LinearLayout.LayoutParams((width/5)*3,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        LinearLayout.LayoutParams lpHappy = new LinearLayout.LayoutParams((width/5)*4,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        LinearLayout.LayoutParams lpSuperHappy = new LinearLayout.LayoutParams(width,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        // Give the mood
        mPreferences = getSharedPreferences("currentMood", MODE_PRIVATE);
        mMood = mPreferences.getInt(MainActivity.BUNDLE_STATE_CURRENT_MOOD, 0);

        if (mMood == 0) {
            mDaySeven.setLayoutParams(lpSad);
        } else if (mMood == 1) {
            mDaySeven.setLayoutParams(lpDisappointed);
        } else if (mMood == 2) {
            mDaySeven.setLayoutParams(lpNormal);
        } else if (mMood == 3) {
            mDaySeven.setLayoutParams(lpHappy);
        } else if (mMood == 4) {
            mDaySeven.setLayoutParams(lpSuperHappy);
        }


        // Save the comment
        mPreferences = getSharedPreferences("Commentaire", MODE_PRIVATE);
        mNote = mPreferences.getString(MainActivity.BUNDLE_STATE_NOTE,null);


        // Display a toast message when click button
        mButtonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HistoricActivity.this, mNote, Toast.LENGTH_SHORT).show();
            }
        });



        // Save the date and print it
        mPreferences = getSharedPreferences("date", MODE_PRIVATE);

        msaveDate = mPreferences.getString(MainActivity.BUNDLE_STATE_DATE, null);

        // yesterday

        mPreferences = getSharedPreferences("yesterday", MODE_PRIVATE);

        hier = mPreferences.getString(MainActivity.BUNDLE_STATE_YESTERDAY, null);

        printDate();

    }


    private void printDate() {
        mDayTwo.setText(msaveDate);
        mDayThree.setText(hier);
    }
}
