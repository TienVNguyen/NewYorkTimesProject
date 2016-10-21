/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * {@link ConfigurationUtils} holds the Configuration functions
 *
 * @author TienVNguyen
 */
public class ConfigurationUtils {
    /**
     * Check orientation
     *
     * @param context {@link Context}
     * @return {@link Boolean}
     */
    public static boolean isLandscape(final Context context) {
        final Configuration configuration = context.getResources().getConfiguration();
        return Configuration.ORIENTATION_LANDSCAPE == configuration.orientation;
    }

    /**
     * Check connection
     *
     * @param context {@link Context}
     * @return {@link Boolean}
     */
    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isConnected();
    }
}
