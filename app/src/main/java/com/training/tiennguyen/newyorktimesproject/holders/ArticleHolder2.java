/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.training.tiennguyen.newyorktimesproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link ArticleHolder2}
 *
 * @author TienVNguyen
 */
public class ArticleHolder2 extends RecyclerView.ViewHolder {
    @BindView(R.id.item2TextViewSnippet)
    public TextView textViewSnippet;

    /**
     * Constructor
     *
     * @param itemView {@link View}
     */
    public ArticleHolder2(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
