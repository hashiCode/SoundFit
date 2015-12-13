package com.hashicode.soundfit;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;
import com.hashicode.soundfit.persistence.SoundFitDatabaseHelper;

/**
 * Created by takahashi on 10/12/15.
 */
public class TestDb extends AndroidTestCase {

    private final String LOG_TAG = TestDb.class.getSimpleName();

    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(SoundFitDatabaseHelper.DB_NAME);
        SQLiteDatabase db = SoundFitDatabaseHelper.getInstance(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        Log.d(LOG_TAG, "DataBase created");
        db.close();
    }

}

