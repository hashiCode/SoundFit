package com.hashicode.soundfit;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import com.hashicode.soundfit.persistence.SoundFitDatabaseHelper;
import com.hashicode.soundfit.persistence.SoundFitService;

/**
 * Created by takahashi on 12/28/15.
 */
public class TestService extends AndroidTestCase {

    private SoundFitService soundFitService;

    public void setUp(){
        mContext.deleteDatabase(SoundFitDatabaseHelper.DB_NAME);
        SQLiteDatabase db = SoundFitDatabaseHelper.getInstance(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
        this.soundFitService = SoundFitService.getInstance(mContext);
    }

    public void testSelectAll(){
        assertEquals(3, soundFitService.selectAllSoundFit().size());
    }

    public void testSelectByType(){
        assertNotNull(soundFitService.selectByType(Constants.BIKING));
        assertNotNull(soundFitService.selectByType(Constants.RUNNING));
        assertNotNull(soundFitService.selectByType(Constants.WALKING));
        assertNull(soundFitService.selectByType("unknown"));
    }

}
