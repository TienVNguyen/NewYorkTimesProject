/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * {@link SearchResultModel}
 *
 * @author TienVNguyen
 */
@Parcel(analyze = SearchResultModel.class)
public class SearchResultModel {

    @SerializedName("docs")
    protected List<ArticleModel> mArticles;

    /**
     * Empty constructor needed by the {@link Parcel} library
     */
    public SearchResultModel() {
    }

    /**
     * Full Constructor
     *
     * @param mArticles {@link List<ArticleModel>}
     */
    public SearchResultModel(final List<ArticleModel> mArticles) {
        this.mArticles = mArticles;
    }

    public List<ArticleModel> getmArticles() {
        return mArticles;
    }
}
