package com.hashicode.soundfit.persistence;

import android.provider.BaseColumns;

/**
 * Created by takahashi on 12/10/15.
 */
public interface SoundFitTable {

    String TABLE_NAME = "soundfit";

    String COLUMN_ID = BaseColumns._ID;
    String COLUMN_TYPE = "type";
    String COLUMN_HEADPHONE_ENABLED = "headphone_enabled";
    String COLUMN_HEADPHONE_VOLUME = "headphone_volume";
    String COLUMN_BLUETOOTH_ENABLED = "bluetooh_enabled";
    String COLUMN_BLUETOOTH_VOLUME = "bluetooth_volume";

    int COLUMN_ID_IDX = 0;
    int COLUMN_TYPE_IDX = 1;
    int COLUMN_HEADPHONE_ENABLED_IDX = 2;
    int COLUMN_HEADPHONE_VOLUME_IDX = 3;
    int COLUMN_BLUETOOTH_ENABLED_IDX = 4;
    int COLUMN_BLUETOOTH_VOLUME_IDX = 5;

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" integer primary key, "
            + COLUMN_TYPE+ " integer not null, "
            + COLUMN_HEADPHONE_ENABLED+" integer not null, "
            + COLUMN_HEADPHONE_VOLUME+" integer not null, "
            + COLUMN_BLUETOOTH_ENABLED+" integer not null, "
            + COLUMN_BLUETOOTH_VOLUME+ " integer not null)";

    String INSERT_TEMPLATE = "insert into "+TABLE_NAME+ "("+COLUMN_TYPE+", "+COLUMN_HEADPHONE_ENABLED
            +", "+COLUMN_HEADPHONE_VOLUME+", "+COLUMN_BLUETOOTH_ENABLED+", "+COLUMN_BLUETOOTH_VOLUME+") values (%d, 1, 35, 0, 20)";
}
