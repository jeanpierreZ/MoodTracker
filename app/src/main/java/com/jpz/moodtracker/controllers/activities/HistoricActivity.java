package com.jpz.moodtracker.controllers.activities;

import android.graphics.Color;
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
import com.jpz.moodtracker.model.Mood;
import com.jpz.moodtracker.model.MySharedPreferences;
import java.util.Calendar;
import java.util.Date;

public class HistoricActivity extends AppCompatActivity {

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

        Button mButtonOne = findViewById(R.id.activity_historic_btn_one);
        Button mButtonTwo = findViewById(R.id.activity_historic_btn_two);
        Button mButtonThree = findViewById(R.id.activity_historic_btn_three);
        Button mButtonFour = findViewById(R.id.activity_historic_btn_four);
        Button mButtonFive = findViewById(R.id.activity_historic_btn_five);
        Button mButtonSix = findViewById(R.id.activity_historic_btn_six);
        Button mButtonSeven = findViewById(R.id.activity_historic_btn_seven);

        TextView mTextViewOne = findViewById(R.id.activity_historic_text_one);
        TextView mTextViewTwo = findViewById(R.id.activity_historic_text_two);
        TextView mTextViewThree = findViewById(R.id.activity_historic_text_three);
        TextView mTextViewFour = findViewById(R.id.activity_historic_text_four);
        TextView mTextViewFive = findViewById(R.id.activity_historic_text_five);
        TextView mTextViewSix = findViewById(R.id.activity_historic_text_six);
        TextView mTextViewSeven = findViewById(R.id.activity_historic_text_seven);

        mTextViewOne.setText(getString(R.string.day_1));
        mTextViewTwo.setText(getString(R.string.day_2));
        mTextViewThree.setText(getString(R.string.day_3));
        mTextViewFour.setText(getString(R.string.day_4));
        mTextViewFive.setText(getString(R.string.day_5));
        mTextViewSix.setText(getString(R.string.day_6));
        mTextViewSeven.setText(getString(R.string.day_7));

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

        Date mOne = calendarOne.getTime();
        Date mTwo = calendarTwo.getTime();
        Date mThree = calendarThree.getTime();
        Date mFour = calendarFour.getTime();
        Date mFive = calendarFive.getTime();
        Date mSix = calendarSix.getTime();
        Date mSeven = calendarSeven.getTime();

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

    private void displayMood(Date mChosenDay, RelativeLayout relativeLayout) {
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
        MySharedPreferences prefs = new MySharedPreferences(this.getApplicationContext());

        Mood pastMood = prefs.getMood(mChosenDay);

        // Display mood width and color
        switch (pastMood) {
            case Sad:
                relativeLayout.setLayoutParams(lpSad);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                break;
            case Disappointed:
                relativeLayout.setLayoutParams(lpDisappointed);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                break;
            case Normal:
                relativeLayout.setLayoutParams(lpNormal);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                break;
            case Happy:
                relativeLayout.setLayoutParams(lpHappy);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                break;
            case SuperHappy:
                relativeLayout.setLayoutParams(lpSuperHappy);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                break;
            default:
                relativeLayout.setBackgroundColor(Color.WHITE);
        }
    }

    private void displayComment(Date mChosenDay, Button button) {
        // Get the comment of the chosen day
        MySharedPreferences prefs = new MySharedPreferences(this.getApplicationContext());

        final String mNote = prefs.getComment(mChosenDay);
        // If there is a comment, display the button
        if (mNote != null && !mNote.isEmpty()) {
            button.setVisibility(View.VISIBLE);
            // Display the comment in a toast message when button's clicked
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Create toast
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    // Create view
                    TextView tvToast =  new TextView(HistoricActivity.this);
                    tvToast.setPadding(40,40,40,40);
                    tvToast.setBackgroundColor(getResources().getColor(R.color.very_dark_grey));
                    tvToast.setTextColor(getResources().getColor(R.color.white));
                    tvToast.setText(mNote);
                    // Display the toast message
                    toast.setView(tvToast);
                    toast.show();
                }
            });
            // If there is no comment, don't display the button
        } else {
            button.setVisibility(View.GONE);
        }
    }
}