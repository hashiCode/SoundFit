package com.hashicode.soundfit.service;

import android.content.Context;
import com.hashicode.soundfit.Constants;

/**
 * Created by takahashi on 1/5/16.
 */
public class SoundFitPreferenceService {

    private Context context;
    private static SoundFitPreferenceService soundFitPreferenceService=null;

    private SoundFitPreferenceService(Context context){
        this.context=context;
    }

    public static SoundFitPreferenceService getInstance(Context context){
        if(soundFitPreferenceService==null) {
            soundFitPreferenceService = new SoundFitPreferenceService(context);
        }
        return soundFitPreferenceService;
    }

    public boolean getGoogleFitActive(){
        return context.getSharedPreferences(Constants.SOUND_FIT_PREFERENCES_NAME,Context.MODE_PRIVATE).getBoolean(Constants.GOOGLE_FIT_ACTIVATE, false);
    }

    public void setGoogleFitActive(boolean isActive){
        this.context.getSharedPreferences(Constants.SOUND_FIT_PREFERENCES_NAME,Context.MODE_PRIVATE).edit().putBoolean(Constants.GOOGLE_FIT_ACTIVATE, isActive).commit();
    }
}
