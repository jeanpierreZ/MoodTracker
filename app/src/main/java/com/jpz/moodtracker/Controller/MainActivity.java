package com.jpz.moodtracker.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jpz.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private View mbackgroundColor;
    private ImageView msmileyMood;
    private Button mconsultHistory;
    private Button maddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbackgroundColor = (View) findViewById(R.id.activity_main_background_color);
        msmileyMood = (ImageView) findViewById(R.id.activity_main_smiley);
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
                        dialog.dismiss();

                        //a toast to control if the string in the EditText is OK before to save it
                        Toast.makeText(getApplicationContext(), input.getText(), Toast.LENGTH_LONG).show();
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
}
