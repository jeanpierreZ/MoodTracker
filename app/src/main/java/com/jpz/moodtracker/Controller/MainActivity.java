package com.jpz.moodtracker.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jpz.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    //private View mbackgroundColor;
    //private ImageView msmileyMood;
    private Button mconsultHistory;

    private Button maddNote;

    private FrameLayout msuperHappyLayout;
    private FrameLayout mhappyLayout;
    private FrameLayout mnormalLayout;

    private GestureDetector mGestureDetector;

    private SharedPreferences mPreferences;

    public static final String BUNDLE_STATE_NOTE = "BUNDLE_STATE_NOTE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidGestureDetector androidGestureDetector = new AndroidGestureDetector();
        mGestureDetector = new GestureDetector(MainActivity.this, androidGestureDetector);

        msuperHappyLayout = (FrameLayout) findViewById(R.id.activity_main_superHappy);
        mhappyLayout = (FrameLayout) findViewById(R.id.activity_main_happy);
        mnormalLayout = (FrameLayout) findViewById(R.id.activity_main_normal);

        //mbackgroundColor = (View) findViewById(R.id.activity_main_background_color);
        //msmileyMood = (ImageView) findViewById(R.id.activity_main_smiley);
        maddNote = (Button) findViewById(R.id.activity_main_note_add);
        mconsultHistory = (Button) findViewById(R.id.activity_main_history);

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
                        mPreferences = getSharedPreferences("test", MODE_PRIVATE);
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

    private void changeUp() {
        msuperHappyLayout.setVisibility(View.VISIBLE);
        mhappyLayout.setVisibility(View.INVISIBLE);
        mnormalLayout.setVisibility(View.INVISIBLE);
    }

    private void changeDown() {
        mnormalLayout.setVisibility(View.VISIBLE);
        mhappyLayout.setVisibility(View.INVISIBLE);
        msuperHappyLayout.setVisibility(View.INVISIBLE);
    }


    class AndroidGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // Swipe the screen down to up
            if (e1.getY() > e2.getY()) {
                changeUp();
            }

            // Swipe the screen up to down
            if (e1.getY() < e2.getY()) {
                changeDown();
            }

            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
