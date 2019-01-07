package com.jpz.moodtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jpz.moodtracker.controllers.fragments.MoodFragment;

public class PageAdapter extends FragmentPagerAdapter {


    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return MoodFragment.newInstance(MoodFragment.Mood.Sad);
            case 1 :
                return MoodFragment.newInstance(MoodFragment.Mood.Disappointed);
            case 2 :
                return MoodFragment.newInstance(MoodFragment.Mood.Normal);
            case 3 :
                return MoodFragment.newInstance(MoodFragment.Mood.Happy);
            case 4 :
                return MoodFragment.newInstance(MoodFragment.Mood.SuperHappy);
            default:
                return MoodFragment.newInstance(MoodFragment.Mood.Happy);
        }
    }
}
