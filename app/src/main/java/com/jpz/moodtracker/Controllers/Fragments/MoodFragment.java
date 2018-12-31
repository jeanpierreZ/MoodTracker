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

    /*
    public static final String KEY_BACKGROUND_COLOR = "KEY_BACKGROUND_COLOR";
    public static final String KEY_SMILEY = "KEY_SMILEY";
    */

    public static final String KEY_MOOD = "KEY_MOOD";

    private ImageView moodColor;
    private ImageView moodSmiley;


    public MoodFragment() {
        // Required empty public constructor
    }


    public enum Mood {
        sadMood(R.color.faded_red, R.drawable.smiley_sad),
        disappointedMood(R.color.warm_grey, R.drawable.smiley_disappointed),
        normalMood(R.color.cornflower_blue_65, R.drawable.smiley_normal),
        happyMood(R.color.light_sage, R.drawable.smiley_happy),
        superHappyMood(R.color.banana_yellow, R.drawable.smiley_super_happy);

        private int color;
        private int drawable;

        Mood(int color, int drawable) {
            this.color = color;
            this.drawable = drawable;
        }

        public int getColor() {
            return color;
        }

        public int getDrawable() {
            return drawable;
        }

        public int moodChoice(Mood x){

            switch (x){
                case sadMood:

                    return sadMood.getColor();

                case disappointedMood:
                    return  disappointedMood.getColor();
                    return  disappointedMood.getDrawable();

                case normalMood:
                    return getColor();
                return getDrawable();

                case happyMood:
                    return getColor();
                return getDrawable();

                case superHappyMood:
                    return getColor();
                return getDrawable();

                default:
                    return R.color.light_sage;
                return R.drawable.smiley_happy;
            }
        }

        }


    public static MoodFragment newInstance(int mood) {

        MoodFragment moodFragment = new MoodFragment();

        Bundle args = new Bundle();

        args.putInt(KEY_MOOD, mood);

        moodFragment.setArguments(args);

        return(moodFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get layout for MoodFragment
        View result = inflater.inflate(R.layout.fragment_mood, container, false);

        // Get widgets from layout
        moodColor = result.findViewById(R.id.fragment_mood_color);
        moodSmiley = result.findViewById(R.id.fragment_mood_smiley);

        // Get data from Bundle
        int mood = getArguments().getInt(KEY_MOOD, 3);


        // Update widgets whit it

        moodColor.setBackgroundColor(getResources().getColor(R.color.faded_red));
        moodSmiley.setImageResource(R.drawable.smiley_sad);

        return result;
    }


}
