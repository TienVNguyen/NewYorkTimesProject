/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.training.tiennguyen.newyorktimesproject.constants.UrlConstants;

import org.parceler.Parcel;

/**
 * {@link MultimediaModel}
 *
 * @author TienVNguyen
 */
public class MultimediaModel implements Parcelable {

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

    private MultimediaModel(android.os.Parcel in) {
        mWidth = in.readInt();
        mUrl = in.readString();
        mHeight = in.readInt();
        mSubtype = in.readString();
        mType = in.readString();
    }

    public static final Creator<MultimediaModel> CREATOR = new Creator<MultimediaModel>() {
        @Override
        public MultimediaModel createFromParcel(android.os.Parcel in) {
            return new MultimediaModel(in);
        }

        @Override
        public MultimediaModel[] newArray(int size) {
            return new MultimediaModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeInt(mWidth);
        dest.writeString(mUrl);
        dest.writeInt(mHeight);
        dest.writeString(mSubtype);
        dest.writeString(mType);
    }
}
