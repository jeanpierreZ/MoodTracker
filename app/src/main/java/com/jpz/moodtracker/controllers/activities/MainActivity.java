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

    private SharedPreferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureViewPager();

        Button maddNote = findViewById(R.id.activity_main_note_add);
        Button mconsultHistory = findViewById(R.id.activity_main_history);

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
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
                        Calendar calendar = Calendar.getInstance();
                        String commentToday = sdf.format(calendar.getTime());

                        // Save the comment
                        mPreferences = getSharedPreferences("Comment", MODE_PRIVATE);
                        String saveNote = input.getText().toString();
                        mPreferences.edit().putString(commentToday, saveNote).apply();
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
                int mlastMood = verticalPager.getCurrentItem();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
                Calendar calendar = Calendar.getInstance();
                String mtoday = sdf.format(calendar.getTime());

                // Save default mood when there is no swipe
                if (position == 3) {
                    mPreferences = getSharedPreferences("Historic", MODE_PRIVATE);
                    mPreferences.edit().putInt(mtoday, mlastMood).apply();

                } // Save mood when there is swipe
                else {
                    mPreferences = getSharedPreferences("Historic", MODE_PRIVATE);
                    mPreferences.edit().putInt(mtoday, mlastMood).apply();
                }
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
