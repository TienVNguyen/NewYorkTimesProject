/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.utils;

import com.training.tiennguyen.newyorktimesproject.constants.UrlConstants;

import java.util.LinkedHashMap;

/**
 * {@link SearchRequest}
 *
 * @author TienVNguyen
 */
public class SearchRequest {

    /**
     * toQueryMap
     *
     * @return {@link LinkedHashMap<>}
     */
    public static LinkedHashMap<String, String> toQueryMap() {
        final LinkedHashMap<String, String> queryMap = new LinkedHashMap<>();
        queryMap.put(UrlConstants.BEGIN_DATE, "20160112");
        queryMap.put(UrlConstants.SORT, "oldest");
        queryMap.put(UrlConstants.FQ, "news_desk");
        queryMap.put(UrlConstants.NEWS_DESK, "Education");
        queryMap.put(UrlConstants.PAGE, "10");
        return queryMap;
    }
}
