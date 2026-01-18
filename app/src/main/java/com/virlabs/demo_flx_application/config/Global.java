package com.virlabs.demo_flx_application.config;

/**
 * CineCraze Configuration
 */

public class Global {
    // Custom API Configuration
    public static final String API_BASE_URL = "https://ronaldtorrejos1.fwh.is/";
    public static final String API_URL = API_BASE_URL + "api.php";
    public static final String AUTH_API_URL = API_BASE_URL + "auth_api.php";
    
    // YouTube API Key
    public static final String Youtube_Key = "AIzaSyAephi0fVTEBXgphX7Z_WVSW8iPusDibtg";
    
    // App Configuration
    public static final String APP_NAME = "CineCraze";
    
    // AdMob Settings (to be fetched from API or set here)
    public static String ADMOB_APP_ID = "";
    public static String ADMOB_BANNER_ID = "";
    public static String ADMOB_INTERSTITIAL_ID = "";
    public static String ADMOB_REWARDED_ID = "";
    public static boolean ADMOB_ENABLED = true;

}



