package com.hashicode.soundfit.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.hashicode.soundfit.Constants;
import com.hashicode.soundfit.model.SoundFit;
import com.hashicode.soundfit.persistence.SoundFitService;

/**
 * Created by takahashi on 12/17/15.
 */
public class SoundFitDetectActivityIntentService extends IntentService {

    private static final String TAG = "SoundFitDetectActivityIntentService";

    private final static String NAME = SoundFitDetectActivityIntentService.class.getCanonicalName();

    private SoundFitService soundFitService;

    public SoundFitDetectActivityIntentService() {
        super(NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DataPoint result = DataPoint.extract(intent);
        AudioManager audioManager =(AudioManager) getApplication().getSystemService(Context.AUDIO_SERVICE);
        Log.d(TAG,"head: "+audioManager.isWiredHeadsetOn()+" bluetooth: "+audioManager.isBluetoothA2dpOn()+" Activity: "+result.getValue(Field.FIELD_ACTIVITY).asActivity());
        if(audioManager.isBluetoothA2dpOn() || audioManager.isWiredHeadsetOn()) {
            float confidence = result.getValue(Field.FIELD_CONFIDENCE).asFloat();
            if (confidence > 50) {
                soundFitService = SoundFitService.getInstance(getApplicationContext());
                SoundFit soundFit = resolveSoundFit(result);
                if (soundFit != null) {
                    setMusicStreamVolume(soundFit, audioManager);
                }

            }
        }
    }

    private void setMusicStreamVolume(SoundFit soundFit, AudioManager audioManager) {
        if(audioManager.isWiredHeadsetOn() && soundFit.getHeadphoneEnable()){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, soundFit.getHeadphoneVolume(), 0);
        }
        else if(soundFit.getBluetoothEnabled()){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, soundFit.getBluetoothVolume(), 0);
        }
    }

    private SoundFit resolveSoundFit(DataPoint result) {
        SoundFit soundFit =null;
        String activity = result.getValue(Field.FIELD_ACTIVITY).asActivity();
        Log.d(TAG,activity);
        switch (activity) {
            case Constants.WALKING:
                soundFit=soundFitService.selectByType(Constants.WALKING);
                break;
            case Constants.RUNNING :
                soundFit=soundFitService.selectByType(Constants.RUNNING);
                break;
            case Constants.BIKING :
                soundFit=soundFitService.selectByType(Constants.BIKING);
                break;
            case Constants.STILL :
                soundFit = soundFitService.selectByType(Constants.STILL);
                break;
        }
        return soundFit;
    }
}
