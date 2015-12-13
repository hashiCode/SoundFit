package com.hashicode.soundfit.listeners;

import android.widget.SeekBar;
import com.hashicode.soundfit.R;
import com.hashicode.soundfit.model.SoundFit;
import com.hashicode.soundfit.tasks.SoundFitUpdateTask;

/**
 * Created by takahashi on 12/15/15.
 */
public class VolumeSeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private SoundFit soundFit;

    public VolumeSeekBarListener(SoundFit soundFit) {
        this.soundFit = soundFit;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int value = seekBar.getProgress();
        int seekBarId = seekBar.getId();
        switch ( seekBarId){
            case R.id.bluetooth_seekbar :
                soundFit.setBluetoothVolume(value);
                break;
            case R.id.headphone_seekbar :
                soundFit.setHeadphoneVolume(value);
                break;
        }
        new SoundFitUpdateTask(seekBar.getContext()).execute(soundFit);
    }
}
