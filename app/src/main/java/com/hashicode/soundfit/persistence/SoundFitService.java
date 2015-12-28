package com.hashicode.soundfit.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.hashicode.soundfit.model.SoundFit;

import java.util.ArrayList;
import java.util.List;

import static com.hashicode.soundfit.persistence.SoundFitTable.*;

/**
 * Created by takahashi on 12/15/15.
 */
public class SoundFitService {

    private static SoundFitService SOUNDFIT_SERVICE = null;
    private SoundFitDatabaseHelper soundFitDatabaseHelper = null;

    private SoundFitService(Context context) {
        this.soundFitDatabaseHelper = SoundFitDatabaseHelper.getInstance(context);
    }

    public static SoundFitService getInstance(Context context){
        if(SOUNDFIT_SERVICE ==null){
            SOUNDFIT_SERVICE = new SoundFitService(context);
        }
        return SOUNDFIT_SERVICE;
    }


    public List<SoundFit> selectAllSoundFit(){
        List<SoundFit> result = new ArrayList<>();
        Cursor cursor = this.soundFitDatabaseHelper.getReadableDatabase().query(SoundFitTable.TABLE_NAME, null, null, null, null, null, SoundFitTable.COLUMN_TYPE);
        for(int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            SoundFit soundFit = new SoundFit();
            soundFit.setId(cursor.getInt(SoundFitTable.COLUMN_ID_IDX));
            soundFit.setType(cursor.getString(SoundFitTable.COLUMN_TYPE_IDX));
            soundFit.setHeadphoneEnable(cursor.getInt(SoundFitTable.COLUMN_HEADPHONE_ENABLED_IDX)==1);
            soundFit.setHeadphoneVolume(cursor.getInt(SoundFitTable.COLUMN_HEADPHONE_VOLUME_IDX));
            soundFit.setBluetoothEnabled(cursor.getInt(SoundFitTable.COLUMN_BLUETOOTH_ENABLED_IDX)==1);
            soundFit.setBluetoothVolume(cursor.getInt(SoundFitTable.COLUMN_BLUETOOTH_VOLUME_IDX));
            result.add(soundFit);
        }
        cursor.close();
        return result;
    }

    public int update(SoundFit soundFit){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SoundFitTable.COLUMN_BLUETOOTH_ENABLED, soundFit.getBluetoothEnabled() ? 1 :0);
        contentValues.put(SoundFitTable.COLUMN_BLUETOOTH_VOLUME, soundFit.getBluetoothVolume());
        contentValues.put(SoundFitTable.COLUMN_HEADPHONE_ENABLED, soundFit.getHeadphoneEnable() ? 1 : 0);
        contentValues.put(SoundFitTable.COLUMN_HEADPHONE_VOLUME, soundFit.getHeadphoneVolume());
        return this.soundFitDatabaseHelper.getWritableDatabase().update(SoundFitTable.TABLE_NAME,contentValues, SoundFitTable.COLUMN_ID + "= ?",new String[]{soundFit.getId().toString()});
    }

    public SoundFit selectByType(String type){
        SoundFit soundFit=null;
        Cursor query = this.soundFitDatabaseHelper.getReadableDatabase().query(SoundFitTable.TABLE_NAME, null, SoundFitTable.COLUMN_TYPE + " =?", new String[]{type}, null, null,null);
        if(query.getCount()==1) {
            soundFit = new SoundFit();
            query.moveToFirst();
            soundFit.setId(query.getInt(COLUMN_ID_IDX));
            soundFit.setBluetoothEnabled(query.getInt(COLUMN_BLUETOOTH_ENABLED_IDX) == 1);
            soundFit.setBluetoothVolume(query.getInt(COLUMN_BLUETOOTH_VOLUME_IDX));
            soundFit.setHeadphoneEnable(query.getInt(COLUMN_HEADPHONE_ENABLED_IDX) == 1);
            soundFit.setHeadphoneVolume(query.getInt(COLUMN_HEADPHONE_VOLUME_IDX));
            query.close();
        }
        return soundFit;
    }


}
