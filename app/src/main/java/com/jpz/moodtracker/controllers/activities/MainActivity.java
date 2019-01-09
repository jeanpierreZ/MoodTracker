package com.jpz.moodtracker.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jpz.moodtracker.R;
import com.jpz.moodtracker.adapters.PageAdapter;
import com.jpz.moodtracker.view.VerticalViewPager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button mconsultHistory;
    private Button maddNote;

    private SharedPreferences mPreferences;

    public static final String BUNDLE_STATE_NOTE = "BUNDLE_STATE_NOTE";

    public static final String BUNDLE_STATE_TODAY = "BUNDLE_STATE_TODAY";

    public static final String BUNDLE_STATE_LAST_MOOD = "BUNDLE_STATE_LAST_MOOD";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Save this day
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        String thisDay = sdf.format(calendar.getTime());
        mPreferences = getSharedPreferences("today", MODE_PRIVATE);
        mPreferences.edit().putString(BUNDLE_STATE_TODAY, thisDay).apply();

        this.configureViewPager();

        maddNote = findViewById(R.id.activity_main_note_add);
        mconsultHistory = findViewById(R.id.activity_main_history);

        maddNote.setEnabled(true);
        mconsultHistory.setEnabled(true);

        mconsultHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the button
                Intent historicActivityIntent = new Intent(MainActivity.this, HistoricActivity.class);
                startActivity(historicActivityIntent);
            }
        });

        maddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                AlertDialog.Builder note = new AlertDialog.Builder(MainActivity.this);
                note.setTitle("Commentaire");
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                note.setView(input);

                note.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Save the comment
                        mPreferences = getSharedPreferences("Commentaire", MODE_PRIVATE);
                        String saveNote = input.getText().toString();
                        mPreferences.edit().putString(BUNDLE_STATE_NOTE, saveNote).apply();
                        dialog.dismiss();
                    }
                });

                note.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                note.show();
            }
        });
    }

    private void configureViewPager() {
        final VerticalViewPager verticalPager = findViewById(R.id.activity_main_viewpager);

        // Attach the page change listener inside the activity
        verticalPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // This method will be invoked when a new page becomes selected
            @Override
            public void onPageSelected(int position) {
                // Save the position of the mood
                int mlastMood = verticalPager.getCurrentItem();
                mPreferences = getSharedPreferences("lastMood", MODE_PRIVATE);

                // Save default mood when there is no swipe
                if (position == 3)
                    mPreferences.edit().putInt(BUNDLE_STATE_LAST_MOOD, mlastMood).apply();
                    // Save mood when there is swipe
                else
                    mPreferences.edit().putInt(BUNDLE_STATE_LAST_MOOD, mlastMood).apply();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        // Display Happy Mood by default
        verticalPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        verticalPager.setCurrentItem(3);
    }
}
