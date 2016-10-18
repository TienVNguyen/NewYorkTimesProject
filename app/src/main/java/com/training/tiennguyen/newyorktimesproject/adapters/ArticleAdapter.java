/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.holders.ArticleHolder1;
import com.training.tiennguyen.newyorktimesproject.holders.ArticleHolder2;
import com.training.tiennguyen.newyorktimesproject.models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ArticleAdapter}
 *
 * @author TienVNguyen
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NORMAL = 0;
    private final int NO_IMAGE = 1;
    private List<ArticleModel> mArticles;

    /**
     * Constructor
     */
    public ArticleAdapter() {
        this.mArticles = new ArrayList<>();
    }

    public void setArticles(List<ArticleModel> mArticles) {
        this.mArticles.clear();
        this.mArticles.addAll(mArticles);
        notifyDataSetChanged();
    }

    public void setMoreArticles(List<ArticleModel> mArticles) {
        this.mArticles.addAll(mArticles);
        notifyItemRangeChanged(mArticles.size() - 1, this.mArticles.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_1, parent, false);
                return new ArticleHolder1(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_2, parent, false);
                return new ArticleHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ArticleModel model = mArticles.get(position);
        if (holder instanceof ArticleHolder1) {
            bindViewHolder1(model, (ArticleHolder1) holder);
        } else {
            bindViewHolder2(model, (ArticleHolder2) holder);
        }

    }

    private void bindViewHolder1(ArticleModel model, ArticleHolder1 holder) {
        holder.textViewSnippet.setText(model.getmSnippet());
        Glide.with(holder.itemView.getContext())
                .load(model.getmMultimedia().get(0).getmUrl())
                .into(holder.imageViewMultimedia);
    }

    private void bindViewHolder2(ArticleModel model, ArticleHolder2 holder) {
        holder.textViewSnippet.setText(model.getmSnippet());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public int getItemViewType(int position) {
        final ArticleModel model = mArticles.get(position);
        if (null == model.getmMultimedia() || model.getmMultimedia().isEmpty())
            return NO_IMAGE;
        return NORMAL;
    }
}
