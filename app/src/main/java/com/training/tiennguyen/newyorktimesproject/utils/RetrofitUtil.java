/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.utils;

import com.google.gson.Gson;
import com.training.tiennguyen.newyorktimesproject.BuildConfig;
import com.training.tiennguyen.newyorktimesproject.constants.UrlConstants;
import com.training.tiennguyen.newyorktimesproject.models.ArticleResponseModel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link RetrofitUtil} holds the Request functions to server
 *
 * @author TienVNguyen
 */
public class RetrofitUtil {

    private static final MediaType JSON = MediaType.parse(UrlConstants.PARSE_JSON);
    private static final Gson GSON = new Gson();

    /**
     * Get
     *
     * @return {@link Retrofit}
     */
    public static Retrofit get() {
        return new Retrofit.Builder()
                .baseUrl(UrlConstants.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get Client
     *
     * @return {@link OkHttpClient}
     */
    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(getApiInterceptor())
                .addInterceptor(getInterceptorForResponse())
                .build();
    }

    /**
     * Get API Interceptor
     *
     * @return {@link Interceptor}
     */
    private static Interceptor getApiInterceptor() {
        return chain -> chain.proceed(getRequest(chain));
    }

    /**
     * Get key Interceptor for Response
     *
     * @return {@link Interceptor}
     */
    private static Interceptor getInterceptorForResponse() {
        return RetrofitUtil::getResponse;
    }

    /**
     * Get Request
     *
     * @param chain {@link Interceptor.Chain}
     * @return {@link Request}
     */
    private static Request getRequest(final Interceptor.Chain chain) {
        final Request request = chain.request();
        return request.newBuilder()
                .url(getUrl(request))
                .build();
    }

    /**
     * Get Response
     *
     * @param chain {@link Interceptor.Chain}
     * @return {@link Request}
     */
    private static Response getResponse(Interceptor.Chain chain) throws IOException {
        final Response response = chain.proceed(chain.request());
        final ResponseBody responseBody = response.body();
        final ArticleResponseModel articleResponseModel = GSON.fromJson(responseBody.string(), ArticleResponseModel.class);
        responseBody.close();

        return response.newBuilder()
                .body(ResponseBody.create(JSON, articleResponseModel.getResponse().toString()))
                .build();
    }

    /**
     * Get Url
     *
     * @param request {@link Request}
     * @return {@link HttpUrl}
     */
    private static HttpUrl getUrl(final Request request) {
        return request.url()
                .newBuilder()
                .addQueryParameter(UrlConstants.BASE_API, BuildConfig.API_KEY)
                .build();
    }

    /**
     * loggingInterceptor
     *
     * @return {@link HttpLoggingInterceptor}
     */
    private static HttpLoggingInterceptor loggingInterceptor() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
