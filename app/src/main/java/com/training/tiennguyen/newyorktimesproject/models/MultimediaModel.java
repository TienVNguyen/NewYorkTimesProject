/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * {@link MultimediaModel}
 *
 * @author TienVNguyen
 */
@Parcel(analyze = {MultimediaModel.class})
public class MultimediaModel {

    @SerializedName("width")
    protected int mWidth;
    @SerializedName("url")
    protected String mUrl;
    @SerializedName("height")
    protected int mHeight;
    @SerializedName("subtype")
    protected String mSubtype;
    @SerializedName("type")
    protected String mType;

    /**
     * Empty constructor needed by the {@link Parcel} library
     */
    public MultimediaModel() {
    }

    /**
     * Full Constructor
     *
     * @param mWidth   {@link Integer}
     * @param mUrl     {@link String}
     * @param mHeight  {@link Integer}
     * @param mSubtype {@link String}
     * @param mType    {@link String}
     */
    public MultimediaModel(final int mWidth, final String mUrl,
                           final int mHeight, final String mSubtype,
                           final String mType) {
        this.mWidth = mWidth;
        this.mUrl = mUrl;
        this.mHeight = mHeight;
        this.mSubtype = mSubtype;
        this.mType = mType;
    }

    public int getmWidth() {
        return mWidth;
    }

    public String getmUrl() {
        return mUrl;
    }

    public int getmHeight() {
        return mHeight;
    }

    public String getmSubtype() {
        return mSubtype;
    }

    public String getmType() {
        return mType;
    }
}
