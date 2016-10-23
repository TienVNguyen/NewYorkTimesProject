/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.constants.DataConstants;
import com.training.tiennguyen.newyorktimesproject.holders.ArticleHolder1;
import com.training.tiennguyen.newyorktimesproject.holders.ArticleHolder2;
import com.training.tiennguyen.newyorktimesproject.listeners.LoadingMoreListener;
import com.training.tiennguyen.newyorktimesproject.models.ArticleModel;
import com.training.tiennguyen.newyorktimesproject.utils.IntentUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * {@link ArticleAdapter}
 *
 * @author TienVNguyen
 */
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NORMAL = 0;
    private List<ArticleModel> mArticles;
    private LoadingMoreListener mLoadingMoreListener;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(DataConstants.DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor
     */
    public ArticleAdapter() {
        this.mArticles = new ArrayList<>();
    }

    /**
     * setArticles
     *
     * @param mArticles {@link List<ArticleModel>}
     */
    public void setArticles(final List<ArticleModel> mArticles) {
        this.mArticles.clear();
        this.mArticles.addAll(mArticles);
        notifyDataSetChanged();
    }

    /**
     * setMoreArticles
     *
     * @param mArticles {@link List<ArticleModel>}
     */
    public void setMoreArticles(final List<ArticleModel> mArticles) {
        this.mArticles.addAll(mArticles);
        notifyItemRangeChanged(getItemCount() - 1, mArticles.size());
    }

    /**
     * setLoadingMoreListener
     *
     * @param mLoadingMoreListener {@link LoadingMoreListener}
     */
    public void setLoadingMoreListener(final LoadingMoreListener mLoadingMoreListener) {
        this.mLoadingMoreListener = mLoadingMoreListener;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ArticleHolder1) {
            ArticleHolder1 holder1 = (ArticleHolder1) holder;
            Glide.clear(holder1.item1ImageViewMultimedia);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_1, parent, false);
                return new ArticleHolder1(view.getContext(), view, mArticles);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_2, parent, false);
                return new ArticleHolder2(view.getContext(), view, mArticles);
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

        if (position == (mArticles.size() - 1) && null != mLoadingMoreListener) {
            mLoadingMoreListener.onLoadMore();
        }
    }

    /**
     * bindViewHolder1
     *
     * @param model  {@link ArticleModel}
     * @param holder {@link ArticleHolder1}
     */
    private void bindViewHolder1(ArticleModel model, ArticleHolder1 holder) {
        setUpMultimedia(model, holder);
        setUpSnippet(model, holder.item1TextViewSnippet);
        setPupDate(model, holder.item1TextViewPubDate);

        holder.item1ImageViewViewShared.setOnClickListener(view ->
                IntentUtils.shareAction(model.getmWebUrl(), holder.itemView.getContext()));
    }

    /**
     * bindViewHolder2
     *
     * @param model  {@link ArticleModel}
     * @param holder {@link ArticleHolder2}
     */
    private void bindViewHolder2(ArticleModel model, ArticleHolder2 holder) {
        setUpSnippet(model, holder.item2textViewSnippet);
        setPupDate(model, holder.item2TextViewPubDate);

        holder.item2ImageViewShared.setOnClickListener(view ->
                IntentUtils.shareAction(model.getmWebUrl(), holder.itemView.getContext()));
    }

    /**
     * setUpMultimedia
     *
     * @param model  {@link ArticleModel}
     * @param holder {@link ArticleHolder1}
     */
    private void setUpMultimedia(final ArticleModel model, final ArticleHolder1 holder) {
        Glide.with(holder.itemView.getContext())
                .load(model.getmMultimedia().get(0).getmUrl())
                .placeholder(R.drawable.image_placeholder)
                .into(holder.item1ImageViewMultimedia);
    }

    /**
     * setUpSnippet
     *
     * @param model   {@link ArticleModel}
     * @param snippet {@link TextView}
     */
    private void setUpSnippet(final ArticleModel model, final TextView snippet) {
        if (null != model.getmSnippet() && 0 < model.getmSnippet().length()) {
            snippet.setText(model.getmSnippet());
        } else {
            snippet.setVisibility(View.GONE);
        }
    }

    /**
     * setPupDate
     *
     * @param model {@link ArticleModel}
     * @param view  {@link TextView}
     */
    private void setPupDate(final ArticleModel model, final TextView view) {
        try {
            final Date pubDate = mSimpleDateFormat.parse(model.getmPubDate());
            String pupDate = mSimpleDateFormat.format(pubDate);
            if (pupDate.length() > 0) {
                view.setText(pupDate);
            } else {
                view.setVisibility(View.GONE);
            }
        } catch (ParseException e) {
            Log.e("ERROR_PARSE_HOLDER2", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    @Override
    public int getItemViewType(int position) {
        final ArticleModel model = mArticles.get(position);
        if (null == model.getmMultimedia() || model.getmMultimedia().isEmpty())
            return 1;
        return NORMAL;
    }
}
