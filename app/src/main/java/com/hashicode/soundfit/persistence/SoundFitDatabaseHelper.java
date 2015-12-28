package com.hashicode.soundfit.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.hashicode.soundfit.Constants;

/**
 * Created by takahashi on 12/10/15.
 */
public class SoundFitDatabaseHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "soundfit.db";
    private final static int DB_VERSION = 1;

    private static SoundFitDatabaseHelper SOUNDFITDATABASEHELPER;

    private SoundFitDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SoundFitDatabaseHelper getInstance(Context context){
        if (SOUNDFITDATABASEHELPER == null) {
            SOUNDFITDATABASEHELPER = new SoundFitDatabaseHelper(context);
        }
        return SOUNDFITDATABASEHELPER;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SoundFitTable.CREATE_TABLE);
        sqLiteDatabase.execSQL(String.format(SoundFitTable.INSERT_TEMPLATE, Constants.WALKING));
        sqLiteDatabase.execSQL(String.format(SoundFitTable.INSERT_TEMPLATE, Constants.RUNNING));
        sqLiteDatabase.execSQL(String.format(SoundFitTable.INSERT_TEMPLATE, Constants.BIKING));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
