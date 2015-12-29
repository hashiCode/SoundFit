package com.hashicode.soundfit.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import com.hashicode.soundfit.R;
import com.hashicode.soundfit.listeners.EnableSwitchOnCheckedListener;
import com.hashicode.soundfit.listeners.VolumeSeekBarListener;
import com.hashicode.soundfit.model.SoundFit;

/**
 * Created by takahashi on 12/7/15.
 */
public class SoundFitFragment extends Fragment {

    private SoundFit soundFit;

    private final static String SOUND_FIT_KEY = "soundFit";

    public static SoundFitFragment createFragment(SoundFit soundFit){
        Bundle args = new Bundle();
        args.putParcelable(SOUND_FIT_KEY, soundFit);
        SoundFitFragment soundFitFragment = new SoundFitFragment();
        soundFitFragment.setArguments(args);
        return soundFitFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {
            Bundle arguments = this.getArguments();
            this.soundFit = arguments.getParcelable(SOUND_FIT_KEY);
        }
        else{
            this.soundFit = savedInstanceState.getParcelable(SOUND_FIT_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SOUND_FIT_KEY, this.soundFit);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        this.initializeView(rootView);
        return rootView;
    }

    private void initializeView(View rootView) {
        AudioManager audioManager =(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        int streamMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);


        Switch headphoneSwitch = (Switch) rootView.findViewById(R.id.headphone_switch);
        Switch bluetoothSwitch = (Switch) rootView.findViewById(R.id.bluetooth_switch);

        SeekBar headphoneSeekBar = (SeekBar) rootView.findViewById(R.id.headphone_seekbar);
        SeekBar bluetoothSeekBar = (SeekBar) rootView.findViewById(R.id.bluetooth_seekbar);
        headphoneSeekBar.setMax(streamMaxVolume);
        bluetoothSeekBar.setMax(streamMaxVolume);

        VolumeSeekBarListener volumeSeekBarListener = new VolumeSeekBarListener(this.soundFit);
        headphoneSeekBar.setOnSeekBarChangeListener(volumeSeekBarListener);
        bluetoothSeekBar.setOnSeekBarChangeListener(volumeSeekBarListener);

        headphoneSwitch.setOnCheckedChangeListener(new EnableSwitchOnCheckedListener(this.soundFit, headphoneSeekBar));
        bluetoothSwitch.setOnCheckedChangeListener(new EnableSwitchOnCheckedListener(this.soundFit, bluetoothSeekBar));

        headphoneSwitch.setChecked(this.soundFit.getHeadphoneEnable());
        bluetoothSwitch.setChecked(this.soundFit.getBluetoothEnabled());

        headphoneSeekBar.setProgress(this.soundFit.getHeadphoneVolume());
        bluetoothSeekBar.setProgress(this.soundFit.getBluetoothVolume());

        headphoneSeekBar.setEnabled(this.soundFit.getHeadphoneEnable());
        bluetoothSeekBar.setEnabled(this.soundFit.getBluetoothEnabled());


    }
}