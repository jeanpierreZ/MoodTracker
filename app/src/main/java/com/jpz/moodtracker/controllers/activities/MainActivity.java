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
import com.jpz.moodtracker.R;
import com.jpz.moodtracker.adapters.PageAdapter;
import com.jpz.moodtracker.controllers.fragments.MoodFragment;
import com.jpz.moodtracker.model.Mood;
import com.jpz.moodtracker.model.MySharedPreferences;
import com.jpz.moodtracker.view.VerticalViewPager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import static com.jpz.moodtracker.model.Mood.Disappointed;
import static com.jpz.moodtracker.model.Mood.Happy;
import static com.jpz.moodtracker.model.Mood.Normal;
import static com.jpz.moodtracker.model.Mood.Sad;
import static com.jpz.moodtracker.model.Mood.SuperHappy;

public class MainActivity extends AppCompatActivity implements MoodFragment.OnSmileyClickedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button maddComment = findViewById(R.id.activity_main_note_add);
        Button mconsultHistoric = findViewById(R.id.activity_main_history);

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
    }

    private void sendSMS() {
        // Get the mood of today
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        Calendar calendar = Calendar.getInstance();
        String mtoday = sdf.format(calendar.getTime());
        /*
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
        */
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

    @Override
    public void OnSmileyClicked(View view) {

        //MySharedPreferences prefs = new MySharedPreferences(this.getApplicationContext());

        Snackbar.make(findViewById(android.R.id.content), "Tu as clické sur le smiley ", Snackbar.LENGTH_SHORT).show();

        /*
        //Initialize value
        Mood mood = Mood.SuperHappy;

        switch (mood) {
            case Sad:
                Snackbar.make(findViewById(android.R.id.content), "Tu as choisi une très mauvaise humeur ", Snackbar.LENGTH_SHORT).show();
                //prefs.saveMood(Calendar.getInstance().getTime(), Sad);
                break;
            case Disappointed:
                Snackbar.make(findViewById(android.R.id.content), "Tu as choisi une mauvaise humeur", Snackbar.LENGTH_SHORT).show();
                //prefs.saveMood(Calendar.getInstance().getTime(), Disappointed);
                break;
            case Normal:
                Snackbar.make(findViewById(android.R.id.content), "Tu as choisi une humeur normale", Snackbar.LENGTH_SHORT).show();
                //prefs.saveMood(Calendar.getInstance().getTime(), Normal);
                break;
            case Happy:
                Snackbar.make(findViewById(android.R.id.content), "Tu as choisi une bonne humeur", Snackbar.LENGTH_SHORT).show();
                //prefs.saveMood(Calendar.getInstance().getTime(), Happy);
                break;
            case SuperHappy:
                Snackbar.make(findViewById(android.R.id.content), "Tu as choisi une super bonne humeur", Snackbar.LENGTH_SHORT).show();
                //prefs.saveMood(Calendar.getInstance().getTime(), SuperHappy);
                break;
        }

*/

    }
}

