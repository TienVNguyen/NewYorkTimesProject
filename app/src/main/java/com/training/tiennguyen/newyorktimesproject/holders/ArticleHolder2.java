/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.activities.WebActivity;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;
import com.training.tiennguyen.newyorktimesproject.models.ArticleModel;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link ArticleHolder2}
 *
 * @author TienVNguyen
 */
public class ArticleHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.item2TextViewSnippet)
    public TextView item2textViewSnippet;
    @BindView(R.id.item2TextViewPubDate)
    public TextView item2TextViewPubDate;
    @BindView(R.id.item2ImageViewShared)
    public ImageView item2ImageViewShared;

    private Context mContext;
    private List<ArticleModel> mArticles;

    /**
     * Constructor
     *
     * @param context  {@link Context}
     * @param itemView {@link View}
     * @param articles {@link List<ArticleModel>}
     */
    public ArticleHolder2(final Context context, final View itemView, final List<ArticleModel> articles) {
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
                final Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra(IntentConstants.PARCELABLE_ARTICLE, Parcels.wrap(model));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        }
    }
}