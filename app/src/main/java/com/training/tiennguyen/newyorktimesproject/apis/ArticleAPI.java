/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.apis;

import com.training.tiennguyen.newyorktimesproject.models.SearchResultModel;

import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * {@link ArticleAPI}
 *
 * @author TienVNguyen
 */
public interface ArticleAPI {

    /**
     * articlesearch.json
     *
     * @param queryMap {@link LinkedHashMap}
     * @return {@link Call<SearchResultModel>}
     */
    @GET("articlesearch.json")
    Call<SearchResultModel> getArticles(final @QueryMap(encoded = true) LinkedHashMap<String, String> queryMap);
}
