package com.jpz.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static android.content.Context.MODE_PRIVATE;

public class MySharedPreferences {

    private static SharedPreferences prefs;

    public MySharedPreferences(Context context){
        prefs = context.getSharedPreferences("TEST", MODE_PRIVATE);
    }

    private String getCommentKey(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        return "Comment_" + sdf.format(date);
    }

    private String getMoodKey(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        return "Mood_" + sdf.format(date);
    }

    public void saveMood(Date date, Mood mood) {
        prefs.edit().putString(getMoodKey(date), mood.name()).apply();
    }

    public Mood getMood(Date date) {
        return Mood.valueOf(prefs.getString(getMoodKey(date), null));
    }

    public void saveComment(Date date, String comment) {
        prefs.edit().putString(getCommentKey(date), comment).apply();
    }

    public String getComment(Date date) {
        return prefs.getString(getCommentKey(date), null);
    }
}