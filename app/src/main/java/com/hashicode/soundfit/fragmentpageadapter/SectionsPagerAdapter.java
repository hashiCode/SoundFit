package com.hashicode.soundfit.fragmentpageadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hashicode.soundfit.Constants;
import com.hashicode.soundfit.fragment.SoundFitFragment;
import com.hashicode.soundfit.model.SoundFit;

import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {



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
        return Constants.TYPES.length;
    }

}
