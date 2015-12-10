package com.hashicode.soundfit.model;

import com.hashicode.soundfit.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by takahashi on 12/8/15.
 */
public class SoundFit {

    public static final int[] TYPES;
    public static final Map<Integer, Integer> TYPE_ICON;

    static{
        TYPES = new int[]{R.string.walking, R.string.running, R.string.biking};
        Map<Integer, Integer> map = new HashMap<>();
        map.put(R.string.walking,R.drawable.ic_directions_walk_white_24dp);
        map.put(R.string.running,R.drawable.ic_directions_run_white_24dp);
        map.put(R.string.biking,R.drawable.ic_directions_bike_white_24dp);
        TYPE_ICON = Collections.unmodifiableMap(map);
    }

}
