/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;
import com.training.tiennguyen.newyorktimesproject.fragments.ConnectionDialogFragment;
import com.training.tiennguyen.newyorktimesproject.models.ArticleModel;
import com.training.tiennguyen.newyorktimesproject.utils.ConfigurationUtils;
import com.training.tiennguyen.newyorktimesproject.utils.IntentUtils;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link WebActivity} holds the Web function to load the URL
 *
 * @author TienVNguyen
 */
public class WebActivity extends AppCompatActivity {
    @BindView(R.id.progressBarWebView)
    protected ProgressBar progressBar;
    @BindView(R.id.webView)
    protected WebView webView;

    private ArticleModel mArticleModel;
    private FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                if (null != mArticleModel.getmWebUrl() && 0 < mArticleModel.getmWebUrl().length())
                    IntentUtils.shareAction(mArticleModel.getmWebUrl(), WebActivity.this);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    /**
     * initViews
     */
    private void initViews() {
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        setUpBackArrow();
        loadingWebView();
    }

    /**
     * loadingWebView
     */
    private void loadingWebView() {
        if (!ConfigurationUtils.isNetworkAvailable(WebActivity.this)) {
            ConnectionDialogFragment connectionDialogFragment = ConnectionDialogFragment.newInstance(
                    false, getString(R.string.connection_error_title));
            connectionDialogFragment.show(mFragmentManager, IntentConstants.DIALOG_CONNECTION_TAG);
        } else {
            setUpArticleModel();
            setUpWebView();
        }
    }

    /**
     * setUpArticleModel
     */
    private void setUpArticleModel() {
        final Intent intent = getIntent();
        if (null != intent)
            mArticleModel = Parcels.unwrap(intent.getParcelableExtra(IntentConstants.PARCELABLE_ARTICLE));
    }

    /**
     * setUpWebView
     */
    private void setUpWebView() {
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new MyBrowser());

        webView.loadUrl(Uri.parse(mArticleModel.getmWebUrl()).toString());
    }

    /**
     * setUpBackArrow
     */
    private void setUpBackArrow() {
        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * Manages the behavior when URLs are loaded
     */
    private class MyBrowser extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }
}
