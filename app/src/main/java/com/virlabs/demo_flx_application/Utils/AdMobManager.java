package com.virlabs.demo_flx_application.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.virlabs.demo_flx_application.config.Global;

public class AdMobManager {
    
    private static final String TAG = "AdMobManager";
    private static InterstitialAd mInterstitialAd;
    private static RewardedAd mRewardedAd;
    
    public static void initializeAdMob(Context context) {
        if (!Global.ADMOB_ENABLED) {
            Log.d(TAG, "AdMob is disabled");
            return;
        }
        
        if (Global.ADMOB_INTERSTITIAL_ID != null && !Global.ADMOB_INTERSTITIAL_ID.isEmpty()) {
            loadInterstitialAd(context);
        }
        
        if (Global.ADMOB_REWARDED_ID != null && !Global.ADMOB_REWARDED_ID.isEmpty()) {
            loadRewardedAd(context);
        }
    }
    
    public static AdView createBannerAd(Context context) {
        if (!Global.ADMOB_ENABLED || Global.ADMOB_BANNER_ID == null || Global.ADMOB_BANNER_ID.isEmpty()) {
            return null;
        }
        
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(Global.ADMOB_BANNER_ID);
        
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        
        return adView;
    }
    
    public static void showBannerAd(Activity activity, ViewGroup container) {
        if (!Global.ADMOB_ENABLED || Global.ADMOB_BANNER_ID == null || Global.ADMOB_BANNER_ID.isEmpty()) {
            return;
        }
        
        AdView adView = createBannerAd(activity);
        if (adView != null) {
            container.removeAllViews();
            container.addView(adView);
        }
    }
    
    private static void loadInterstitialAd(Context context) {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            return;
        }
        
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(Global.ADMOB_INTERSTITIAL_ID);
        
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                loadInterstitialAd(context);
            }
            
            @Override
            public void onAdFailedToLoad(LoadAdError error) {
                Log.e(TAG, "Interstitial ad failed to load: " + error.getMessage());
            }
        });
        
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }
    
    public static void showInterstitialAd(Context context) {
        if (!Global.ADMOB_ENABLED || Global.ADMOB_INTERSTITIAL_ID == null || Global.ADMOB_INTERSTITIAL_ID.isEmpty()) {
            return;
        }
        
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            loadInterstitialAd(context);
        }
    }
    
    private static void loadRewardedAd(Context context) {
        if (mRewardedAd != null) {
            return;
        }
        
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded(RewardedAd ad) {
                mRewardedAd = ad;
            }
            
            @Override
            public void onRewardedAdFailedToLoad(LoadAdError error) {
                Log.e(TAG, "Rewarded ad failed to load: " + error.getMessage());
                mRewardedAd = null;
            }
        };
        
        RewardedAd.load(context, Global.ADMOB_REWARDED_ID, 
                       new AdRequest.Builder().build(), adLoadCallback);
    }
    
    public static void showRewardedAd(Activity activity, RewardedAdCallback callback) {
        if (!Global.ADMOB_ENABLED || Global.ADMOB_REWARDED_ID == null || Global.ADMOB_REWARDED_ID.isEmpty()) {
            if (callback != null) {
                callback.onRewardEarned();
            }
            return;
        }
        
        if (mRewardedAd != null) {
            mRewardedAd.show(activity, rewardItem -> {
                if (callback != null) {
                    callback.onRewardEarned();
                }
                loadRewardedAd(activity);
            });
        } else {
            loadRewardedAd(activity);
            if (callback != null) {
                callback.onAdNotAvailable();
            }
        }
    }
    
    public interface RewardedAdCallback {
        void onRewardEarned();
        void onAdNotAvailable();
    }
}
