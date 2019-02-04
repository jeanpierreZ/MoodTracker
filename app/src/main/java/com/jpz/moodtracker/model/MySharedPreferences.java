package com.jpz.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    private SharedPreferences prefs;

    // Constructor
    public MySharedPreferences(Context context){
        prefs = context.getSharedPreferences("TEST", MODE_PRIVATE);
    }

    // Get a key, which is a date, for comments
    private String getCommentKey(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        return "Comment_" + sdf.format(date);
    }

    // Get a key, which is a date, for moods
    private String getMoodKey(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        return "Mood_" + sdf.format(date);
    }

    // Save the mood on a chosen date with the key
    public void saveMood(Date date, Mood mood) {
        prefs.edit().putString(getMoodKey(date), mood.name()).apply();
    }

    // Get the mood on a chosen date
    public Mood getMood(Date date) {
        String moodValue = prefs.getString(getMoodKey(date), null);
        if (moodValue != null) {
            return Mood.valueOf(moodValue);
        }
        // If there is no historical, don't return a mood
        return null;
    }

    // Save the comment on a chosen date with the key
    public void saveComment(Date date, String comment) {
        prefs.edit().putString(getCommentKey(date), comment).apply();
    }

    // Get the comment on a chosen date
    public String getComment(Date date) {
        return prefs.getString(getCommentKey(date), null);
    }
}