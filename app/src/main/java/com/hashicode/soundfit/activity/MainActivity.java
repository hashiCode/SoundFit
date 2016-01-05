package com.hashicode.soundfit.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.SensorRequest;
import com.hashicode.soundfit.Constants;
import com.hashicode.soundfit.R;
import com.hashicode.soundfit.fragmentpageadapter.SectionsPagerAdapter;
import com.hashicode.soundfit.model.SoundFit;
import com.hashicode.soundfit.persistence.SoundFitService;
import com.hashicode.soundfit.service.SoundFitDetectActivityIntentService;
import com.hashicode.soundfit.service.SoundFitPreferenceService;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status> {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private final static String TAG = "soundFitMainActivity";

    private GoogleApiClient googleApiClient;

    private static final int REQUEST_OAUTH = 1;

    private boolean authInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViewPager();
        setupGoogleApiClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!googleApiClient.isConnected()) {
            this.googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.googleApiClient.disconnect();
    }

    private void setupViewPager() {
        List<SoundFit> soundFits = SoundFitService.getInstance(this).selectAllSoundFit();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), soundFits);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        for(int i = 0; i< soundFits.size(); i++){
            tabLayout.getTabAt(i).setIcon(Constants.TYPE_ICON.get(soundFits.get(i).getType()));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupGoogleApiClient() {
        if(googleApiClient==null) {
            this.googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Fitness.SENSORS_API)
                    .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Connected");
        this.setupGoogleFitApi();
    }

    private void setupGoogleFitApi() {
        SoundFitPreferenceService soundFitPreferenceService = SoundFitPreferenceService.getInstance(this);
        if(!soundFitPreferenceService.getGoogleFitActive()) {
            Log.d(TAG, "Adding Sensor");
            SensorRequest sensorRequest = new SensorRequest.Builder()
                    .setDataType(DataType.TYPE_ACTIVITY_SAMPLE)
                    .setSamplingRate(Constants.TIME_SAMPLER, TimeUnit.SECONDS)
                    .setAccuracyMode(SensorRequest.ACCURACY_MODE_HIGH)
                    .build();
            Fitness.SensorsApi.add(
                    googleApiClient,
                    sensorRequest, getSoundFitDetectActivityIntentService());
            soundFitPreferenceService.setGoogleFitActive(true);
        }
        else{
            Log.d(TAG, "Sensor already added");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
            Log.i(TAG, "Connection lost.  Cause: Network Lost.");
        } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
            Log.i(TAG, "Connection lost.  Reason: Service Disconnected");
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed. Cause: " + result.toString());
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                    MainActivity.this, 0).show();
            return;
        }
        if (!authInProgress) {
            try {
                Log.i(TAG, "Attempting to resolve failed connection");
                authInProgress = true;
                result.startResolutionForResult(MainActivity.this,
                        REQUEST_OAUTH);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG,
                        "Exception while starting resolution activity", e);
            }
        }
    }

    private PendingIntent getSoundFitDetectActivityIntentService() {
        Intent intent = new Intent(this, SoundFitDetectActivityIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onResult(Status status) {
        if(status.isSuccess()){
            Log.d(TAG, status.getStatusMessage() + " "+status.getStatusCode());
        }
    }
}
