package com.jpz.moodtracker.Controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jpz.moodtracker.R;

public class HistoricActivity extends AppCompatActivity {

    private TextView mComment;
    private String mNote;
    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mComment = (TextView) findViewById(R.id.activity_historic_comment);

        mPref = getSharedPreferences("test", MODE_PRIVATE);

        mNote = mPref.getString(MainActivity.BUNDLE_STATE_NOTE,null);

        printComment();
    }

    private void printComment() {
        String printTheText = mNote;
        mComment.setText(printTheText);
    }
}
