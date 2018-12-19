package com.jpz.moodtracker.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jpz.moodtracker.Controllers.Fragments.MoodFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int[] color;
    private int[] smiley;

    public PageAdapter(FragmentManager mgr, int[] color, int[] smiley) {
        super(mgr);
        this.color = color;
        this.smiley = smiley;
    }

    @Override
    public int getCount() {
        return (5);
    }

    @Override
    public Fragment getItem(int position) {
        return (MoodFragment.newInstance(this.color[position], this.smiley[position]));
    }
}
