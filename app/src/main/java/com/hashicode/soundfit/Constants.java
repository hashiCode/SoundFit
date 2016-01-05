package com.hashicode.soundfit;

import com.google.android.gms.fitness.FitnessActivities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by takahashi on 12/28/15.
 */
public final class Constants {

    static{
        Map<String, Integer> map = new HashMap<>();
        map.put(Constants.WALKING,R.drawable.ic_directions_walk_white_24dp);
        map.put(Constants.RUNNING,R.drawable.ic_directions_run_white_24dp);
        map.put(Constants.BIKING,R.drawable.ic_directions_bike_white_24dp);
        map.put(Constants.STILL,R.drawable.ic_pause_circle_outline_white_24dp);
        TYPE_ICON = Collections.unmodifiableMap(map);
    }


    public static final String WALKING = FitnessActivities.WALKING;
    public static final String RUNNING = FitnessActivities.RUNNING;
    public static final String BIKING = FitnessActivities.BIKING;
    public static final String STILL = FitnessActivities.STILL;
    public static final String[] TYPES = new String[]{WALKING, RUNNING, BIKING,STILL};
    public static final Map<String, Integer> TYPE_ICON;

    public static final int TIME_SAMPLER = 1;

    public static final String SOUND_FIT_PREFERENCES_NAME = "SoundFitPreferences";
    public static final String GOOGLE_FIT_ACTIVATE = "googleFitActive";
}
