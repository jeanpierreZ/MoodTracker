package com.jpz.moodtracker.Controllers.Fragments;

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

    // Keys for our Bundle

    public static final String KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR";
    public static final String KEY_SMILEY = "KEY_SMILEY";

    //public static final String KEY_MOOD = "KEY_MOOD";

    public MoodFragment() {
        // Required empty public constructor
    }

    public static MoodFragment newInstance(int color, int smiley) {

        MoodFragment mood = new MoodFragment();

        Bundle args = new Bundle();

        args.putInt(KEY_BACKGROUND_COLOR, color);
        args.putInt(KEY_SMILEY, smiley);
        mood.setArguments(args);

        return(mood);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get layout for MoodFragment
        View result = inflater.inflate(R.layout.fragment_mood, container, false);

        // Get widgets from layout
        RelativeLayout fragmentMood = result.findViewById(R.id.fragment_mood_color);
        ImageView moodSmiley = result.findViewById(R.id.fragment_mood_smiley);

        // Get data from Bundle

        int color = getArguments().getInt(KEY_BACKGROUND_COLOR, -1);
        int smiley = getArguments().getInt(KEY_SMILEY, -1);

        // Update widgets whit it

        fragmentMood.setBackgroundColor(color);
        moodSmiley.setImageResource(smiley);

        return result;
    }

}
