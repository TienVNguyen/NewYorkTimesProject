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
    protected JSONObject response;
    @SerializedName("status")
    protected String status;
    @SerializedName("copyright")
    protected String copyright;

    public JSONObject getResponse() {
        if (response == null) {
            return new JSONObject();
        }
        return response;
    }

    public String getmStatus() {
        return status;
    }
}
