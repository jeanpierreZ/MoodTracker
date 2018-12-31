package com.jpz.moodtracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jpz.moodtracker.controllers.fragments.MoodFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int[] mood;

    public PageAdapter(FragmentManager mgr, int[] mood) {
        super(mgr);
        this.mood = mood;
    }

    @Override
    public int getCount() {
        return (5);
    }

    @Override
    public Fragment getItem(int position) {
        return (MoodFragment.newInstance(this.mood[position]));
    }
}
