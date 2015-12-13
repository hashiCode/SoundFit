package com.hashicode.soundfit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;
import com.hashicode.soundfit.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by takahashi on 12/8/15.
 */
public class SoundFit implements Parcelable {

    public static final int WALKING = 0;
    public static final int RUNNING = 1;
    public static final int BIKING = 2;

    public static final int[] TYPES = new int[]{WALKING, RUNNING, BIKING};

    private Integer id;
    private Integer type;
    private Boolean headphoneEnable;
    private Integer headphoneVolume;
    private Boolean bluetoothEnabled;
    private Integer bluetoothVolume;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getHeadphoneEnable() {
        return headphoneEnable;
    }

    public void setHeadphoneEnable(Boolean headphoneEnable) {
        this.headphoneEnable = headphoneEnable;
    }

    public Integer getHeadphoneVolume() {
        return headphoneVolume;
    }

    public void setHeadphoneVolume(Integer headphoneVolume) {
        this.headphoneVolume = headphoneVolume;
    }

    public Boolean getBluetoothEnabled() {
        return bluetoothEnabled;
    }

    public void setBluetoothEnabled(Boolean bluetoothEnabled) {
        this.bluetoothEnabled = bluetoothEnabled;
    }

    public Integer getBluetoothVolume() {
        return bluetoothVolume;
    }

    public void setBluetoothVolume(Integer bluetoothVolume) {
        this.bluetoothVolume = bluetoothVolume;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.type);
        dest.writeValue(this.headphoneEnable);
        dest.writeValue(this.headphoneVolume);
        dest.writeValue(this.bluetoothEnabled);
        dest.writeValue(this.bluetoothVolume);
    }

    public SoundFit() {
    }

    protected SoundFit(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
        this.headphoneEnable = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.headphoneVolume = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bluetoothEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.bluetoothVolume = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<SoundFit> CREATOR = new Parcelable.Creator<SoundFit>() {
        public SoundFit createFromParcel(Parcel source) {
            return new SoundFit(source);
        }

        public SoundFit[] newArray(int size) {
            return new SoundFit[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoundFit soundFit = (SoundFit) o;

        return id != null ? id.equals(soundFit.id) : soundFit.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
