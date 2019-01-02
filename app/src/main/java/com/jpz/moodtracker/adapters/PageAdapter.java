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
        return (5);
    }

    @Override
    public Fragment getItem(int position) {
        return (MoodFragment.newInstance(MoodFragment.Mood.Happy));
    }
}
