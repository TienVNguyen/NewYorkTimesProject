/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import com.google.gson.annotations.SerializedName;
import com.training.tiennguyen.newyorktimesproject.constants.UrlConstants;

import org.parceler.Parcel;

/**
 * {@link MultimediaModel}
 *
 * @author TienVNguyen
 */
@Parcel
public class MultimediaModel {

    @SerializedName("width")
    int mWidth;
    @SerializedName("url")
    String mUrl;
    @SerializedName("height")
    int mHeight;
    @SerializedName("subtype")
    String mSubtype;
    @SerializedName("type")
    String mType;

    /**
     * Empty constructor needed by the {@link Parcel} library
     */
    MultimediaModel() {
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
        return UrlConstants.STATIC_BASE_URL + mUrl;
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
