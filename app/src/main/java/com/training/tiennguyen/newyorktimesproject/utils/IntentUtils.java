/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * {@link IntentUtils}
 *
 * @author TienNguyen
 */
public class IntentUtils {

    /**
     * shareAction
     *
     * @param webUrl  {@link String}
     * @param context {@link Context}
     */
    public static void shareAction(final String webUrl, final Context context) {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Interesting Article");
            String sAux = "\nLet me recommend you this article:\n\n";
            sAux = sAux + webUrl + " \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            context.startActivity(Intent.createChooser(i, "Please choose one!"));
        } catch (Exception e) {
            Log.e("SHARE_ERROR", e.getMessage());
        }
    }
}
