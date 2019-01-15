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
import android.widget.Toast;

import com.jpz.moodtracker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HistoricActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        RelativeLayout mDayOne = findViewById(R.id.activity_historic_day_one);
        RelativeLayout mDayTwo = findViewById(R.id.activity_historic_day_two);
        RelativeLayout mDayThree = findViewById(R.id.activity_historic_day_three);
        RelativeLayout mDayFour = findViewById(R.id.activity_historic_day_four);
        RelativeLayout mDayFive = findViewById(R.id.activity_historic_day_five);
        RelativeLayout mDaySix = findViewById(R.id.activity_historic_day_six);
        RelativeLayout mDaySeven = findViewById(R.id.activity_historic_day_seven);

        Button mButtonOne = findViewById(R.id.activity_historic_one_btn);
        Button mButtonTwo = findViewById(R.id.activity_historic_two_btn);
        Button mButtonThree = findViewById(R.id.activity_historic_three_btn);
        Button mButtonFour = findViewById(R.id.activity_historic_four_btn);
        Button mButtonFive = findViewById(R.id.activity_historic_five_btn);
        Button mButtonSix = findViewById(R.id.activity_historic_six_btn);
        Button mButtonSeven = findViewById(R.id.activity_historic_seven_btn);

        // Create historic from yesterday to seven days ago
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);

        Calendar calendarOne = Calendar.getInstance();
        Calendar calendarTwo = Calendar.getInstance();
        Calendar calendarThree = Calendar.getInstance();
        Calendar calendarFour = Calendar.getInstance();
        Calendar calendarFive = Calendar.getInstance();
        Calendar calendarSix = Calendar.getInstance();
        Calendar calendarSeven = Calendar.getInstance();

        calendarOne.add(Calendar.MINUTE,-1);
        calendarTwo.add(Calendar.MINUTE,-2);
        calendarThree.add(Calendar.MINUTE,-3);
        calendarFour.add(Calendar.MINUTE,-4);
        calendarFive.add(Calendar.MINUTE,-5);
        calendarSix.add(Calendar.MINUTE,-6);
        calendarSeven.add(Calendar.MINUTE,-7);

        String mOne = sdf.format(calendarOne.getTime());
        String mTwo = sdf.format(calendarTwo.getTime());
        String mThree = sdf.format(calendarThree.getTime());
        String mFour = sdf.format(calendarFour.getTime());
        String mFive = sdf.format(calendarFive.getTime());
        String mSix = sdf.format(calendarSix.getTime());
        String mSeven = sdf.format(calendarSeven.getTime());

        this.displayMood(mOne, mDayOne);
        this.displayMood(mTwo, mDayTwo);
        this.displayMood(mThree, mDayThree);
        this.displayMood(mFour, mDayFour);
        this.displayMood(mFive, mDayFive);
        this.displayMood(mSix, mDaySix);
        this.displayMood(mSeven, mDaySeven);

        this.displayComment(mOne, mButtonOne);
        this.displayComment(mTwo, mButtonTwo);
        this.displayComment(mThree, mButtonThree);
        this.displayComment(mFour, mButtonFour);
        this.displayComment(mFive, mButtonFive);
        this.displayComment(mSix, mButtonSix);
        this.displayComment(mSeven, mButtonSeven);
    }

    private void displayMood(String mchosenDay, RelativeLayout relativeLayout) {
        // Change the width of the layout
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

        // Load the mood of the chosen day
        mPreferences = getSharedPreferences("Historic", MODE_PRIVATE);
        int mMood = mPreferences.getInt(mchosenDay, -1);

        // Display mood width and color
        switch (mMood) {
            case 0:
                relativeLayout.setLayoutParams(lpSad);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                break;
            case 1:
                relativeLayout.setLayoutParams(lpDisappointed);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                break;
            case 2:
                relativeLayout.setLayoutParams(lpNormal);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                break;
            case 3:
                relativeLayout.setLayoutParams(lpHappy);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                break;
            case 4:
                relativeLayout.setLayoutParams(lpSuperHappy);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                break;
            default:
                relativeLayout.setBackgroundColor(0);
        }
    }

    private void displayComment(String mchosenDay, Button button) {
        // Load the comment
        mPreferences = getSharedPreferences("Comment", MODE_PRIVATE);
        final String mNote = mPreferences.getString(mchosenDay,null);

        // If there is a comment, display the button
        if (mNote != null && !mNote.isEmpty()) {
            button.setVisibility(View.VISIBLE);
            button.setEnabled(true);
            // Display a toast message when click button
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HistoricActivity.this, mNote, Toast.LENGTH_SHORT).show();
                }
            });
        // If no comment, don't display the button
        } else {
            button.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
        }
    }
}
