/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;


/**
 * {@link ArticleResponseModel}
 *
 * @author TienVNguyen
 */
public class ArticleResponseModel {

    @SerializedName("response")
    private JsonObject response;
    @SerializedName("status")
    private String status;
    @SerializedName("copyright")
    private String copyright;

    public JsonObject getResponse() {
        if (null == response) {
            response = new JsonObject();
        }
        return response;
    }

    public String getmStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }
}
