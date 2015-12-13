package com.hashicode.soundfit.fragmentpageadapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hashicode.soundfit.R;
import com.hashicode.soundfit.fragment.SoundFitFragment;
import com.hashicode.soundfit.model.SoundFit;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public static final Map<Integer, Integer> TYPE_ICON;

    static{
        Map<Integer, Integer> map = new HashMap<>();
        map.put(SoundFit.WALKING,R.drawable.ic_directions_walk_white_24dp);
        map.put(SoundFit.RUNNING,R.drawable.ic_directions_run_white_24dp);
        map.put(SoundFit.BIKING,R.drawable.ic_directions_bike_white_24dp);
        TYPE_ICON = Collections.unmodifiableMap(map);
    }

    private List<SoundFit> sounds;

    public SectionsPagerAdapter(FragmentManager fm, List<SoundFit> listSoundFit) {
        super(fm);
        this.sounds = listSoundFit;
    }

    @Override
    public Fragment getItem(int position) {
        return SoundFitFragment.createFragment(sounds.get(position));
    }

    @Override
    public int getCount() {
        return SoundFit.TYPES.length;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return this.context.getResources().getString(TYPES[position]);
//    }

}
