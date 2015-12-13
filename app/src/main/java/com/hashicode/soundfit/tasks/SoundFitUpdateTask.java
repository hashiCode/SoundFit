package com.hashicode.soundfit.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.hashicode.soundfit.model.SoundFit;
import com.hashicode.soundfit.persistence.SoundFitService;

/**
 * Created by takahashi on 12/15/15.
 */
public class SoundFitUpdateTask extends AsyncTask<SoundFit, Void, Void> {

    private SoundFitService soundFitService;

    public SoundFitUpdateTask(Context context){
        this.soundFitService = SoundFitService.getInstance(context);
    }

    @Override
    protected Void doInBackground(SoundFit... params) {
        SoundFit sound = params[0];
        this.soundFitService.update(sound);
        return null;
    }
}
