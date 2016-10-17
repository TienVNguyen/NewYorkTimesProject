/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;

/**
 * This activity holds the Splash function to load an image.
 *
 * @author TienVNguyen
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    /**
     * Init the view for Layout
     */
    private void initView() {
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(getRunnable(), IntentConstants.SPLASH_TIME_OUT);
    }

    /**
     * Get the action after the time out.
     *
     * @return {@link Runnable}
     */
    @NonNull
    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
