package com.hashicode.soundfit.fragmentpageadapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hashicode.soundfit.fragment.SoundFitFragment;
import com.hashicode.soundfit.model.SoundFit;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return new SoundFitFragment();
    }

    @Override
    public int getCount() {
        return SoundFit.TYPES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.context.getResources().getString(SoundFit.TYPES[position]);
    }

}
