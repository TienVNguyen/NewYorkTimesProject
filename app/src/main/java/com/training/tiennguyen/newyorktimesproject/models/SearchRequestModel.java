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
    private boolean mArts;
    private boolean mFashionStyle;
    private boolean mSports;
    private int mPage;

    /**
     * toQueryMap
     *
     * @return {@link LinkedHashMap<>}
     */
    public LinkedHashMap<String, String> toQueryMap() {
        final LinkedHashMap<String, String> queryMap = new LinkedHashMap<>();
        if (null != mQuery && 0 < mQuery.length()) {
            queryMap.put(UrlConstants.Q, mQuery);
        }
        if (null != mBeginDate && 0 < mBeginDate.length()) {
            queryMap.put(UrlConstants.BEGIN_DATE, mBeginDate.replaceAll("-", ""));
        }
        if (null != mSort && 0 < mSort.length()) {
            queryMap.put(UrlConstants.SORT, mSort.toLowerCase());
        }
        if (mArts || mFashionStyle || mSports) {
            final StringBuilder builder = new StringBuilder();
            if (mArts) {
                builder.append(UrlConstants.FQ_ARTS);
            }
            if (mFashionStyle) {
                builder.append(" ");
                builder.append(UrlConstants.FQ_FASHION_STYLE);
            }
            if (mSports) {
                builder.append(" ");
                builder.append(UrlConstants.FQ_SPORTS);
            }
            queryMap.put(UrlConstants.FQ, "news_desk:(" + builder.toString().trim() + ")");
        }
        queryMap.put(UrlConstants.PAGE, String.valueOf(mPage));
        return queryMap;
    }

    public String getmBeginDate() {
        return mBeginDate;
    }

    public void setmBeginDate(String mBeginDate) {
        this.mBeginDate = mBeginDate;
    }

    public String getmSort() {
        return mSort;
    }

    public void setmSort(String mSort) {
        this.mSort = mSort;
    }

    public boolean ismArts() {
        return mArts;
    }

    public void setmArts(boolean mArts) {
        this.mArts = mArts;
    }

    public boolean ismFashionStyle() {
        return mFashionStyle;
    }

    public void setmFashionStyle(boolean mFashionStyle) {
        this.mFashionStyle = mFashionStyle;
    }

    public boolean ismSports() {
        return mSports;
    }

    public void setmSports(boolean mSports) {
        this.mSports = mSports;
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

    public void resetQuery() {
        this.mQuery = "";
    }
}
