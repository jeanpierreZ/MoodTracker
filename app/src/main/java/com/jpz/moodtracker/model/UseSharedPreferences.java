package com.jpz.moodtracker.model;

import android.content.SharedPreferences;

import com.jpz.moodtracker.controllers.fragments.MoodFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UseSharedPreferences {

    private SharedPreferences mPreferences;

    private String getKey(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        return sdf.format(calendar.getTime());
    }

    public void saveMood(MoodFragment.Mood mood, Calendar calendar) {
        mPreferences.edit().putString(getKey(calendar), mood.toString()).apply();
    }

    public void saveComment(String comment, Calendar calendar) {
        mPreferences.edit().putString(getKey(calendar), comment).apply();
    }

    public void getMood(Calendar calendar) {
        mPreferences.getString(getKey(calendar), null);
    }

    public void getComment(String comment, Calendar calendar) {
        mPreferences.getString(getKey(calendar), null);
    }
}