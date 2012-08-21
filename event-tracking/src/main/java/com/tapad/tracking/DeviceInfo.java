package com.tapad.tracking;

import android.content.Context;
import android.webkit.WebView;

public class DeviceInfo {

    /**
     * Check if the screen configuration indicates that this is a tablet.
     */
    public static boolean isTablet(Context context) {
        return context.getResources().getConfiguration().screenLayout == 4; // SCREENLAYOUT_SIZE_XLARGE
    }

    public static String getUserAgent(Context context) {
        WebView wv = new WebView(context);
        String userAgent = wv.getSettings().getUserAgentString();
        wv.destroy();

        return userAgent;
//        if (isTablet(context)) return "Android Tablet/TapadEventAPI/1.0";
//        else return "Android Mobile/TapadEventAPI/1.0";
    }
}
