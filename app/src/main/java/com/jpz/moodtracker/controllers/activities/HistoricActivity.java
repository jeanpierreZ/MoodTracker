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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HistoricActivity extends AppCompatActivity {

    private RelativeLayout mDayOne;
    private RelativeLayout mDayTwo;
    private RelativeLayout mDayThree;
    private RelativeLayout mDayFour;
    private RelativeLayout mDayFive;
    private RelativeLayout mDaySix;
    private RelativeLayout mDaySeven;


    private Button mButtonOne;
    private Button mButtonTwo;
    private Button mButtonThree;
    private Button mButtonFour;
    private Button mButtonFive;
    private Button mButtonSix;
    private Button mButtonSeven;

    private String mNote;

    private int mMood = 0;

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

        mButtonOne = findViewById(R.id.activity_historic_one_btn);
        mButtonTwo = findViewById(R.id.activity_historic_two_btn);
        mButtonThree = findViewById(R.id.activity_historic_three_btn);
        mButtonFour = findViewById(R.id.activity_historic_four_btn);
        mButtonFive = findViewById(R.id.activity_historic_five_btn);
        mButtonSix = findViewById(R.id.activity_historic_six_btn);
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

        // Get the width's raw of the sad mood
        LinearLayout.LayoutParams lpSad = new LinearLayout.LayoutParams(width/5,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        // Get the width's raw of the disappointed mood
        LinearLayout.LayoutParams lpDisappointed = new LinearLayout.LayoutParams((width/5)*2,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        // Get the width's raw of the normal mood
        LinearLayout.LayoutParams lpNormal = new LinearLayout.LayoutParams((width/5)*3,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        // Get the width's raw of the happy mood
        LinearLayout.LayoutParams lpHappy = new LinearLayout.LayoutParams((width/5)*4,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        // Get the width's raw of the super happy mood
        LinearLayout.LayoutParams lpSuperHappy = new LinearLayout.LayoutParams(width,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);

        // Load the last day when the mood is choose
        mPreferences = getSharedPreferences("today", MODE_PRIVATE);
        String lastDay = mPreferences.getString(MainActivity.BUNDLE_STATE_TODAY, null);

        // Get today
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String today = sdf.format(calendar.getTime());

        // Compare if the last day of the mood selected is different that today
        if(!lastDay.equals(today)){

            // Load the last mood
            mPreferences = getSharedPreferences("lastMood", MODE_PRIVATE);
            mMood = mPreferences.getInt(MainActivity.BUNDLE_STATE_LAST_MOOD, 0);

            // Set mood's attributes
            if (mMood == 0) {
                mDaySeven.setLayoutParams(lpSad);
                mDaySeven.setBackgroundColor(getResources().getColor(R.color.faded_red));
            } else if (mMood == 1) {
                mDaySeven.setLayoutParams(lpDisappointed);
                mDaySeven.setBackgroundColor(getResources().getColor(R.color.warm_grey));
            } else if (mMood == 2) {
                mDaySeven.setLayoutParams(lpNormal);
                mDaySeven.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
            } else if (mMood == 3) {
                mDaySeven.setLayoutParams(lpHappy);
                mDaySeven.setBackgroundColor(getResources().getColor(R.color.light_sage));
            } else if (mMood == 4) {
                mDaySeven.setLayoutParams(lpSuperHappy);
                mDaySeven.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
            } else {
                mDaySeven.setLayoutParams(lpHappy);
                mDaySeven.setBackgroundColor(getResources().getColor(R.color.light_sage));
            }
            // The last mood was selected today, so there is no mood's attribute to register in the raw
        } else {
            mDaySeven.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }


        // Load the comment
        mPreferences = getSharedPreferences("Commentaire", MODE_PRIVATE);
        mNote = mPreferences.getString(MainActivity.BUNDLE_STATE_NOTE,null);


        // Display a toast message when click button
        mButtonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HistoricActivity.this, mNote, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
