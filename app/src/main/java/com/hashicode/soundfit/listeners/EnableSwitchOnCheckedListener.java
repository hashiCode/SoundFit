package com.hashicode.soundfit.listeners;

import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import com.hashicode.soundfit.R;
import com.hashicode.soundfit.model.SoundFit;
import com.hashicode.soundfit.tasks.SoundFitUpdateTask;

/**
 * Created by takahashi on 12/15/15.
 */
public class EnableSwitchOnCheckedListener implements Switch.OnCheckedChangeListener {

    private SoundFit soundFit;
    private SeekBar seekbar;

    public EnableSwitchOnCheckedListener(SoundFit soundFit, SeekBar seekbar) {
        this.soundFit = soundFit;
        this.seekbar = seekbar;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Switch sw = (Switch) buttonView;
        int id = sw.getId();
        seekbar.setEnabled(isChecked);
        switch (id){
            case R.id.bluetooth_switch :
                this.soundFit.setBluetoothEnabled(isChecked);
                break;
            case R.id.headphone_switch :
                this.soundFit.setHeadphoneEnable(isChecked);
                break;
        }
        new SoundFitUpdateTask(buttonView.getContext()).execute(soundFit);
    }
}
