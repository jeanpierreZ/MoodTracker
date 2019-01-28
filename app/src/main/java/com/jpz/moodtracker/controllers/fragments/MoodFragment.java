package com.jpz.moodtracker.controllers.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jpz.moodtracker.R;
import com.jpz.moodtracker.model.Mood;

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

        // Action when click on the smiley
        moodSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(moodColor, "tu as choisi une humeur", Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(getContext(), "je suis clické", Toast.LENGTH_SHORT).show();
            }
        });

        // Get data from Bundle
        final Mood mood = (Mood) getArguments().getSerializable(KEY_MOOD);

        switch (mood) {
            // Update widgets whit it
            case Sad:
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
