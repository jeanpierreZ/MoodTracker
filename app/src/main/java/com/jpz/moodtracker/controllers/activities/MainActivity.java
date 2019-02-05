package com.jpz.moodtracker.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jpz.moodtracker.R;
import com.jpz.moodtracker.adapters.PageAdapter;
import com.jpz.moodtracker.controllers.fragments.MoodFragment;
import com.jpz.moodtracker.model.Mood;
import com.jpz.moodtracker.model.MySharedPreferences;
import com.jpz.moodtracker.view.VerticalViewPager;
import java.util.Calendar;
import java.util.Date;
import static com.jpz.moodtracker.model.Mood.Disappointed;
import static com.jpz.moodtracker.model.Mood.Happy;
import static com.jpz.moodtracker.model.Mood.Normal;
import static com.jpz.moodtracker.model.Mood.Sad;
import static com.jpz.moodtracker.model.Mood.SuperHappy;

public class MainActivity extends AppCompatActivity implements MoodFragment.OnSmileyClickedListener {

    private MySharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addComment = findViewById(R.id.activity_main_note_add);
        Button consultHistoric = findViewById(R.id.activity_main_history);

        prefs = new MySharedPreferences(getApplicationContext());

        // Call VerticalViewPager
        this.configureViewPager();

        // Configure historic button
        consultHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historicActivityIntent = new Intent(MainActivity.this, HistoricActivity.class);
                startActivity(historicActivityIntent);
            }
        });

        // Configure comment button
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                // Create a dialog
                AlertDialog.Builder note = new AlertDialog.Builder(MainActivity.this);
                note.setTitle(getString(R.string.titleComment));
                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                note.setView(input);

                // Configure positive button
                note.setPositiveButton(getString(R.string.validateComment), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Save the comment
                        Date today = Calendar.getInstance().getTime();
                        prefs.saveComment(today, input.getText().toString());
                        dialog.dismiss();
                    }
                });

                // Configure negative button
                note.setNegativeButton(getString(R.string.cancelComment), new DialogInterface.OnClickListener() {
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
        // Display Vertical ViewPager with Happy Mood by default
        VerticalViewPager verticalViewPager = findViewById(R.id.activity_main_viewpager);
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        verticalViewPager.setCurrentItem(3);
    }

    private void sendSMS() {
        // Get the mood of today
        Date today = Calendar.getInstance().getTime();
        Mood dailyMood = prefs.getMood(today);

        // If no mood has been chosen, user can't send message and get an error message
        if (dailyMood == null) {
            Toast.makeText(getApplicationContext(), getString(R.string.errorMessage), Toast.LENGTH_SHORT).show();
        } else {
            // Send the mood
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            switch (dailyMood) {
                case Sad:
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sad));
                    break;
                case Disappointed:
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.disappointed));
                    break;
                case Normal:
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.normal));
                    break;
                case Happy:
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.happy));
                    break;
                case SuperHappy:
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.superHappy));
                    break;
            }
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, Intent.EXTRA_TEXT));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Display an "app bar" or "action bar"
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Display "share" and how to respond when a user clicks on
        switch (item.getItemId()) {
            case R.id.action_share:
                sendSMS();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnSmileyClicked(View view) {
        Date today = Calendar.getInstance().getTime();
        // Create and personalize snackbar
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "", 750);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Save Mood and display a snackbar confirmation
        VerticalViewPager verticalViewPager = findViewById(R.id.activity_main_viewpager);
        switch (verticalViewPager.getCurrentItem()) {
            case 0:
                textView.setText(getString(R.string.sad));
                prefs.saveMood(today, Sad);
                break;
            case 1:
                textView.setText(getString(R.string.disappointed));
                prefs.saveMood(today, Disappointed);
                break;
            case 2:
                textView.setText(getString(R.string.normal));
                prefs.saveMood(today, Normal);
                break;
            case 3:
                textView.setText(getString(R.string.happy));
                prefs.saveMood(today, Happy);
                break;
            case 4:
                textView.setText(getString(R.string.superHappy));
                prefs.saveMood(today, SuperHappy);
                break;
        }
        snackbar.show();
    }
}
