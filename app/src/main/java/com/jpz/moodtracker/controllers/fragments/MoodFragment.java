package com.jpz.moodtracker.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jpz.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoodFragment extends Fragment {

    // Key for Bundle
    public static final String KEY_MOOD = "KEY_MOOD";

    private RelativeLayout moodColor;
    private ImageView moodSmiley;

    public MoodFragment() {
        // Required empty public constructor
    }

    public enum Mood {
        Sad,
        Disappointed,
        Normal,
        Happy,
        SuperHappy
    }

    public static MoodFragment newInstance(Mood mood) {

        MoodFragment moodFragment = new MoodFragment();

        Bundle args = new Bundle();
        args.putSerializable(KEY_MOOD, mood);
        moodFragment.setArguments(args);
        return(moodFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get layout for MoodFragment
        View result = inflater.inflate(R.layout.fragment_mood, container, false);

        // Get widgets from layout
        moodColor = result.findViewById(R.id.fragment_mood);
        moodSmiley = result.findViewById(R.id.fragment_mood_smiley);

        // Get data from Bundle
        Mood mood = (Mood) getArguments().getSerializable(KEY_MOOD);

        switch (mood) {

            case Sad:
                // Update widgets whit it
                moodColor.setBackgroundColor(getResources().getColor(R.color.faded_red));
                moodSmiley.setImageResource(R.drawable.smiley_sad);
                break;

            case Disappointed:
                moodColor.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                moodSmiley.setImageResource(R.drawable.smiley_disappointed);
                break;

            case Normal:
                moodColor.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                moodSmiley.setImageResource(R.drawable.smiley_normal);
                break;

            case Happy:
                moodColor.setBackgroundColor(getResources().getColor(R.color.light_sage));
                moodSmiley.setImageResource(R.drawable.smiley_happy);
                break;

            case SuperHappy:
                moodColor.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                moodSmiley.setImageResource(R.drawable.smiley_super_happy);
                break;

            default:
                moodColor.setBackgroundColor(getResources().getColor(R.color.light_sage));
                moodSmiley.setImageResource(R.drawable.smiley_happy);
        }
        return result;
    }
}
