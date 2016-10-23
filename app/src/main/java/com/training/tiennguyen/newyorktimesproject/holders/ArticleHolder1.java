/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.holders;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.helpers.CustomTabActivityHelper;
import com.training.tiennguyen.newyorktimesproject.models.ArticleModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link ArticleHolder1}
 *
 * @author TienVNguyen
 */
public class ArticleHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.item1ImageViewMultimedia)
    public ImageView item1ImageViewMultimedia;
    @BindView(R.id.item1TextViewSnippet)
    public TextView item1TextViewSnippet;
    @BindView(R.id.item1TextViewPubDate)
    public TextView item1TextViewPubDate;
    @BindView(R.id.item1ImageViewShared)
    public ImageView item1ImageViewViewShared;

    private Context mContext;
    private List<ArticleModel> mArticles;

    /**
     * Constructor
     *
     * @param context  {@link Context}
     * @param itemView {@link View}
     * @param articles {@link List<ArticleModel>}
     */
    public ArticleHolder1(final Context context, final View itemView, final List<ArticleModel> articles) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.mContext = context;
        this.mArticles = articles;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            final ArticleModel model = mArticles.get(position);
            if (model != null) {
                showBrowser(model.getmWebUrl());
            }
        }
    }

    /**
     * showBrowser
     *
     * @param url {@link String}
     */
    private void showBrowser(final String url) {
        final Bitmap shareButton = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_share_black_24dp);

        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);

        final PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext,
                100,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        final CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        builder.setActionButton(shareButton, "Share Link", pendingIntent, true);
        builder.addDefaultShareMenuItem();

        final CustomTabsIntent customTabsIntent = builder.build();
        CustomTabActivityHelper.openCustomTab((Activity) mContext, customTabsIntent, Uri.parse(url),
                (activity, uri) -> {
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                    activity.startActivity(intent1);
                });
    }
}
