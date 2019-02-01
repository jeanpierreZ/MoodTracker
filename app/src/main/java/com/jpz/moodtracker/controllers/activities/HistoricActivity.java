package com.jpz.moodtracker.controllers.activities;

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

        RelativeLayout[] layouts = {mDayOne, mDayTwo, mDayThree, mDayFour, mDayFive, mDaySix, mDaySeven};
        Button[] buttons = {mButtonOne, mButtonTwo, mButtonThree, mButtonFour, mButtonFive, mButtonSix, mButtonSeven};

        // Loop to display moods and comments in the seven past days
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            calendar.add(Calendar.MINUTE, -1);
            this.displayMood(calendar.getTime(), layouts[i]);
            this.displayComment(calendar.getTime(), buttons[i]);
        }
    }

    private void displayMood(Date date, RelativeLayout relativeLayout) {
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

        // Load the mood of the chosen day
        MySharedPreferences prefs = new MySharedPreferences(this.getApplicationContext());
        Mood mood = prefs.getMood(date);

        // Display mood width and color
        switch (mood) {
            case Sad:
                relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(width / 5,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                break;
            case Disappointed:
                relativeLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 5) * 2,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                break;
            case Normal:
                relativeLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 5) * 3,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                break;
            case Happy:
                relativeLayout.setLayoutParams(new LinearLayout.LayoutParams((width / 5) * 4,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                break;
            case SuperHappy:
                relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(width,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                break;
            default:
                relativeLayout.setBackgroundColor(0);
        }
    }

    private void displayComment(Date date, Button button) {
        // Get the comment of the chosen day
        MySharedPreferences prefs = new MySharedPreferences(this.getApplicationContext());
        final String mNote = prefs.getComment(date);

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
                    // Personalize toast view
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