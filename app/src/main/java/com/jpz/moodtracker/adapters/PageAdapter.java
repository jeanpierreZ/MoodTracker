package com.jpz.moodtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.jpz.moodtracker.controllers.fragments.MoodFragment;
import com.jpz.moodtracker.model.Mood;

public class PageAdapter extends FragmentPagerAdapter {


    // Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    // Number of page to show
    @Override
    public int getCount() {
        return 5;
    }

    // Page to return according to the mood
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return MoodFragment.newInstance(Mood.Sad);
            case 1 :
                return MoodFragment.newInstance(Mood.Disappointed);
            case 2 :
                return MoodFragment.newInstance(Mood.Normal);
            case 3 :
                return MoodFragment.newInstance(Mood.Happy);
            case 4 :
                return MoodFragment.newInstance(Mood.SuperHappy);
            default:
                return MoodFragment.newInstance(Mood.Happy);
        }
    }
}
