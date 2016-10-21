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
 * {@link ArticleModel}
 *
 * @author TienVNguyen
 */
@Parcel
public class ArticleModel {

    @SerializedName("web_url")
    String mWebUrl;
    @SerializedName("snippet")
    String mSnippet;
    @SerializedName("lead_paragraph")
    String mLeadParagraph;
    @SerializedName("multimedia")
    List<MultimediaModel> mMultimedia;
    @SerializedName("pub_date")
    String mPubDate;

    /**
     * Empty constructor needed by the {@link Parcel} library
     */
    ArticleModel() {
    }

    /**
     * Full Constructor
     *
     * @param mWebUrl        {@link String}
     * @param mSnippet       {@link String}
     * @param mLeadParagraph {@link String}
     * @param mMultimedia    {@link List<MultimediaModel>}
     * @param mPubDate       {@link String}
     */
    public ArticleModel(final String mWebUrl, final String mSnippet, final String mLeadParagraph,
                        final List<MultimediaModel> mMultimedia, final String mPubDate) {
        this.mWebUrl = mWebUrl;
        this.mSnippet = mSnippet;
        this.mLeadParagraph = mLeadParagraph;
        this.mMultimedia = mMultimedia;
        this.mPubDate = mPubDate;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public String getmSnippet() {
        return mSnippet;
    }

    public String getmLeadParagraph() {
        return mLeadParagraph;
    }

    public List<MultimediaModel> getmMultimedia() {
        return mMultimedia;
    }

    public String getmPubDate() {
        return mPubDate;
    }
}
