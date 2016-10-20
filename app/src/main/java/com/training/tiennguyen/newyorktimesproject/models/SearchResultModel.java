/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * {@link SearchResultModel}
 *
 * @author TienVNguyen
 */
public class SearchResultModel implements Parcelable{

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

    private SearchResultModel(android.os.Parcel in) {
        mArticles = in.createTypedArrayList(ArticleModel.CREATOR);
    }

    public static final Creator<SearchResultModel> CREATOR = new Creator<SearchResultModel>() {
        @Override
        public SearchResultModel createFromParcel(android.os.Parcel in) {
            return new SearchResultModel(in);
        }

        @Override
        public SearchResultModel[] newArray(int size) {
            return new SearchResultModel[size];
        }
    };

    public List<ArticleModel> getmArticles() {
        return mArticles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeTypedList(mArticles);
    }
}
