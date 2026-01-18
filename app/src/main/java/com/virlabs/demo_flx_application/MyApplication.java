package com.virlabs.demo_flx_application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.ads.MobileAds;
import com.orhanobut.hawk.Hawk;
import com.virlabs.demo_flx_application.api.ApiClient;

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;

    protected String mUserAgent;

    @Override
    public void onCreate() {
        MultiDex.install(this);
        Hawk.init(this).build();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        
        // Initialize AdMob only
        MobileAds.initialize(this, initializationStatus -> {});
        
        super.onCreate();
        instance = this;

        // Initialize API Client
        ApiClient.init(this);
        
        mUserAgent = Util.getUserAgent(this, "CineCraze");
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {

        }
    }
    public static MyApplication getInstance ()
    {
        return instance;
    }
//    private void initCast() {
//         Cast Coach staging : CC1AD845
//        CastConfiguration options = new CastConfiguration.Builder("CC1AD845")
//                .enableAutoReconnect()
//                .enableCaptionManagement()
//                .enableDebug()
//                .enableLockScreen()
//                .enableWifiReconnection()
//                .enableNotification()
//                .addNotificationAction(CastConfiguration.NOTIFICATION_ACTION_PLAY_PAUSE, true)
//                .addNotificationAction(CastConfiguration.NOTIFICATION_ACTION_DISCONNECT, true)
//                .build();
//
//        VideoCastManager.initialize(this, options);
//    }

   /* public DataSource.Factory buildDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(this, bandwidthMeter,
                buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(mUserAgent, bandwidthMeter);
    }*/
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    public boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    public static boolean hasNetwork ()
    {
        return instance.checkIfHasNetwork();
    }

}
