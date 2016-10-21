/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.models;

import com.training.tiennguyen.newyorktimesproject.constants.UrlConstants;

import java.util.LinkedHashMap;

/**
 * {@link SearchRequestModel}
 *
 * @author TienVNguyen
 */
public class SearchRequestModel {
    private String mQuery;
    private String mBeginDate;
    private String mSort;
    private String mNewsDesk;
    private int mPage;

    /**
     * toQueryMap
     *
     * @return {@link LinkedHashMap<>}
     */
    public LinkedHashMap<String, String> toQueryMap() {
        final LinkedHashMap<String, String> queryMap = new LinkedHashMap<>();
        if (null != mQuery && 0 > mQuery.length()) {
            queryMap.put(UrlConstants.Q, mQuery);
        }
        if (null != mBeginDate && 0 > mBeginDate.length()) {
            queryMap.put(UrlConstants.BEGIN_DATE, mBeginDate);
        }
        if (null != mSort && 0 > mSort.length()) {
            queryMap.put(UrlConstants.SORT, mSort);
        }
        if (null != mNewsDesk && 0 > mNewsDesk.length()) {
            queryMap.put(UrlConstants.FQ, mNewsDesk);
        }
        queryMap.put(UrlConstants.PAGE, String.valueOf(mPage));
        return queryMap;
    }

    public String getmQuery() {
        return mQuery;
    }

    public String getmBeginDate() {
        return mBeginDate;
    }

    public String getmSort() {
        return mSort;
    }

    public String getmNewsDesk() {
        return mNewsDesk;
    }

    public int getmPage() {
        return mPage;
    }

    public int nextPage() {
        return mPage++;
    }

    public int resetPage() {
        return mPage = 0;
    }

    public void setmQuery(String mQuery) {
        this.mQuery = mQuery;
    }

    public void setmBeginDate(String mBeginDate) {
        this.mBeginDate = mBeginDate;
    }

    public void setmSort(String mSort) {
        this.mSort = mSort;
    }

    public void setmNewsDesk(String mNewsDesk) {
        this.mNewsDesk = mNewsDesk;
    }

    public void setmPage(int mPage) {
        this.mPage = mPage;
    }
}
