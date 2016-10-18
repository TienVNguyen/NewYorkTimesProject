/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * {@link ArticleResponseModel}
 *
 * @author TienVNguyen
 */
public class ArticleResponseModel {

    @SerializedName("response")
    protected JSONObject mResponse;
    @SerializedName("status")
    protected String mStatus;

    public JSONObject getmResponse() {
        if (mResponse == null) {
            return new JSONObject();
        }
        return mResponse;
    }

    public String getmStatus() {
        return mStatus;
    }
}
