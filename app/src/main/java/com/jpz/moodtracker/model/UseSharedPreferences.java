package com.jpz.moodtracker.model;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static android.content.Context.MODE_PRIVATE;

public class UseSharedPreferences {

    private static SharedPreferences mPreferences;

    // variable to hold context
    public Context context;

    //save the context received via constructor in a local variable
    public UseSharedPreferences(Context context){
        this.context = context;
        mPreferences = context.getSharedPreferences("TEST", MODE_PRIVATE);
    }

    private static String getKey(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.FRANCE);
        return sdf.format(date);
    }

    public static void saveMood(Date date, Mood mood) {
        mPreferences.edit().putString(getKey(date), mood.toString()).apply();
    }

    public static String getMood(Date date) {
        return mPreferences.getString(getKey(date), null);
    }

    public static void saveComment(Date date, String comment) {
        mPreferences.edit().putString(getKey(date), comment).apply();
    }

    public static String getComment(Date date) {
        return mPreferences.getString(getKey(date), null);
    }
}