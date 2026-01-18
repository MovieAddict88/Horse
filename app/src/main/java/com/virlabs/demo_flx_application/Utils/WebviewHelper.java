package com.virlabs.demo_flx_application.Utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * WebviewHelper - Bypass free hosting restrictions (InfinityFree, etc.)
 * Uses WebView to handle HTTP requests with proper User-Agent and headers
 * to bypass restrictions imposed by free hosting providers
 */
public class WebviewHelper {
    
    private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android " + Build.VERSION.RELEASE + 
            ") AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36";
    
    /**
     * Fetch content from URL using WebView to bypass free hosting restrictions
     */
    public static String fetchWithWebView(Context context, String url) {
        final String[] result = {null};
        final CountDownLatch latch = new CountDownLatch(1);
        
        try {
            android.os.Handler mainHandler = new android.os.Handler(context.getMainLooper());
            mainHandler.post(() -> {
                try {
                    WebView webView = new WebView(context);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setDomStorageEnabled(true);
                    webSettings.setUserAgentString(USER_AGENT);
                    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
                    
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            view.evaluateJavascript(
                                "(function() { return document.documentElement.outerHTML; })();",
                                html -> {
                                    if (html != null) {
                                        result[0] = html.replace("\\u003C", "<")
                                                       .replace("\\n", "\n")
                                                       .replace("\\\"", "\"")
                                                       .replace("\\\\", "\\");
                                        if (result[0].startsWith("\"") && result[0].endsWith("\"")) {
                                            result[0] = result[0].substring(1, result[0].length() - 1);
                                        }
                                    }
                                    latch.countDown();
                                }
                            );
                        }
                        
                        @Override
                        public void onReceivedError(WebView view, int errorCode, 
                                                  String description, String failingUrl) {
                            latch.countDown();
                        }
                    });
                    
                    webView.loadUrl(url);
                } catch (Exception e) {
                    e.printStackTrace();
                    latch.countDown();
                }
            });
            
            // Wait for response with timeout
            latch.await(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result[0];
    }
    
    /**
     * Fetch content using standard HTTP with proper headers
     * This is a fallback method when WebView is not needed
     */
    public static String fetchWithHttpClient(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // Set headers to bypass free hosting restrictions
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                byte[] buffer = new byte[1024];
                StringBuilder response = new StringBuilder();
                int bytesRead;
                
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.append(new String(buffer, 0, bytesRead));
                }
                
                inputStream.close();
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Fetch content with automatic fallback
     * Tries standard HTTP first, falls back to WebView if needed
     */
    public static String fetch(Context context, String url) {
        // Try standard HTTP first (faster)
        String result = fetchWithHttpClient(url);
        
        // If failed and context is available, try WebView
        if ((result == null || result.isEmpty()) && context != null) {
            result = fetchWithWebView(context, url);
        }
        
        return result;
    }
    
    /**
     * Get headers for HTTP requests to bypass restrictions
     */
    public static Map<String, String> getBypassHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", USER_AGENT);
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Accept-Language", "en-US,en;q=0.9");
        headers.put("Connection", "keep-alive");
        headers.put("Upgrade-Insecure-Requests", "1");
        return headers;
    }
    
    /**
     * Configure WebView for optimal free hosting compatibility
     */
    public static void configureWebView(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUserAgentString(USER_AGENT);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
    }
}
