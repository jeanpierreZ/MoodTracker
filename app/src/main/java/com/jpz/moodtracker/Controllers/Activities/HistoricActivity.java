package com.jpz.moodtracker.Controllers.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jpz.moodtracker.R;

public class HistoricActivity extends AppCompatActivity {

    private TextView mComment;
    private TextView mDate;

    private TextView mYesterday;

    private String mNote;
    private String msaveDate;

    private String hier;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mComment = (TextView) findViewById(R.id.activity_historic_comment);
        mDate = (TextView) findViewById(R.id.activity_historic_date);

        mYesterday = findViewById(R.id.activity_historic_yesterday);

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
        String printTheText = mNote;
        mComment.setText(printTheText);
    }

    private void printDate() {
        //String printTheDate = msaveDate;
        mDate.setText(msaveDate);
        mYesterday.setText(hier);
    }
}
