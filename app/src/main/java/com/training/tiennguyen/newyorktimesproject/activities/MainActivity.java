/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.adapters.ArticleAdapter;
import com.training.tiennguyen.newyorktimesproject.apis.ArticleAPI;
import com.training.tiennguyen.newyorktimesproject.listeners.LoadingListener;
import com.training.tiennguyen.newyorktimesproject.listeners.LoadingMoreListener;
import com.training.tiennguyen.newyorktimesproject.models.SearchResultModel;
import com.training.tiennguyen.newyorktimesproject.utils.RetrofitUtil;
import com.training.tiennguyen.newyorktimesproject.utils.SearchRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link MainActivity} holds the Main function to load the search option
 *
 * @author TienVNguyen
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.progressBarLoading)
    protected ProgressBar progressBarLoading;
    @BindView(R.id.progressBarMore)
    protected ProgressBar progressBarMore;
    @BindView(R.id.recyclerViewArticles)
    protected RecyclerView recyclerViewArticles;

    private ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    /**
     * Init the views for Layout
     */
    private void initViews() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        populateDataForList();
    }

    /**
     * populateDataForList
     */
    private void populateDataForList() {
        mAdapter = new ArticleAdapter();
        mAdapter.setLoadingMoreListener(MainActivity.this::searchMore);

        recyclerViewArticles.setAdapter(mAdapter);
        recyclerViewArticles.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        search();
    }

    /**
     * search
     */
    private void search() {
        fetchArticles(body -> {
            if (body!= null && body.getmArticles().size() > 0)
                mAdapter.setArticles(body.getmArticles());
        });
    }

    /**
     * searchMore
     */
    private void searchMore() {
        progressBarLoading.setVisibility(View.VISIBLE);
        fetchArticles(body -> mAdapter.setMoreArticles(body.getmArticles()));
    }

    /**
     * fetchArticles
     *
     * @param listener {@link LoadingListener}
     */
    private void fetchArticles(final LoadingListener listener) {
        ArticleAPI api = RetrofitUtil.get().create(ArticleAPI.class);
        api.getArticles(SearchRequest.toQueryMap())
                .enqueue(new Callback<SearchResultModel>() {
                    @Override
                    public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
                        onResponseFunction(listener, response);
                    }

                    @Override
                    public void onFailure(Call<SearchResultModel> call, Throwable t) {
                        onFailureFunction(t);
                    }
                });
    }

    /**
     * onFailureFunction
     *
     * @param t {@link Throwable}
     */
    private void onFailureFunction(final Throwable t) {
        Log.d("ARTICLE_FAILED", t.getMessage());
    }

    /**
     * onResponseFunction
     *
     * @param listener {@link LoadingListener}
     * @param response {@link Response<SearchResultModel>}
     */
    private void onResponseFunction(final LoadingListener listener, final Response<SearchResultModel> response) {
        Log.d("ARTICLE_RESPONSE", String.valueOf(response.isSuccessful()));

        listener.onLoading(response.body());
        progressBarMore.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.GONE);
    }
}
