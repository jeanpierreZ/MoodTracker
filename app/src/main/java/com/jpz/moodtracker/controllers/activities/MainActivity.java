package com.jpz.moodtracker.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.jpz.moodtracker.R;
import com.jpz.moodtracker.adapters.PageAdapter;
import com.jpz.moodtracker.model.MySharedPreferences;
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

        Button maddComment = findViewById(R.id.activity_main_note_add);
        Button mconsultHistoric = findViewById(R.id.activity_main_history);

        // Create instance of MySharedPreferences class
        final MySharedPreferences prefs = new MySharedPreferences(this.getApplicationContext());

        this.configureViewPager();

        // Configure historic button
        mconsultHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historicActivityIntent = new Intent(MainActivity.this, HistoricActivity.class);
                startActivity(historicActivityIntent);
            }
        });

        // Configure comment button
        maddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
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
                        prefs.saveComment(Calendar.getInstance().getTime(), input.getText().toString());
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
        final VerticalViewPager verticalViewPager = findViewById(R.id.activity_main_viewpager);
        // Display Vertical ViewPager and Happy Mood by default
        verticalViewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        verticalViewPager.setCurrentItem(3);
        verticalViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Save mood when touch the screen
                int mlastMood = verticalViewPager.getCurrentItem();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
                Calendar calendar = Calendar.getInstance();
                String mtoday = sdf.format(calendar.getTime());
                mPreferences = getSharedPreferences("Historic", MODE_PRIVATE);
                // Return false for action_move/up/down to preserve swipes of verticalViewPager
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mPreferences.edit().putInt(mtoday, mlastMood).apply();
                    return false;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mPreferences.edit().putInt(mtoday, mlastMood).apply();
                    return false;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mPreferences.edit().putInt(mtoday, mlastMood).apply();
                    return false;
                }
                return true;
            }
        });
    }

    private void sendSMS() {
        // Get the mood of today
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        Calendar calendar = Calendar.getInstance();
        String mtoday = sdf.format(calendar.getTime());
        mPreferences = getSharedPreferences("Historic", MODE_PRIVATE);
        int mMood = mPreferences.getInt(mtoday, -1);

        // Send the mood
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        switch (mMood) {
            case 0:
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sad));
                break;
            case 1:
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.disappointed));
                break;
            case 2:
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.normal));
                break;
            case 3:
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.happy));
                break;
            case 4:
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.superHappy));
                break;
        }
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, Intent.EXTRA_TEXT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                sendSMS();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
