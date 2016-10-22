/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.adapters.ArticleAdapter;
import com.training.tiennguyen.newyorktimesproject.apis.ArticleAPI;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;
import com.training.tiennguyen.newyorktimesproject.fragments.FilterDialogFragment;
import com.training.tiennguyen.newyorktimesproject.listeners.FilterDialogListener;
import com.training.tiennguyen.newyorktimesproject.listeners.LoadingListener;
import com.training.tiennguyen.newyorktimesproject.models.SearchRequestModel;
import com.training.tiennguyen.newyorktimesproject.models.SearchResultModel;
import com.training.tiennguyen.newyorktimesproject.utils.ConfigurationUtils;
import com.training.tiennguyen.newyorktimesproject.utils.RetrofitUtil;

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
public class MainActivity extends AppCompatActivity implements FilterDialogListener {
    @BindView(R.id.swipeRefreshLayoutMain)
    protected SwipeRefreshLayout swipeRefreshLayoutMain;
    @BindView(R.id.relativeLayoutLoading)
    protected RelativeLayout relativeLayoutLoading;
    @BindView(R.id.progressBarLoading)
    protected ProgressBar progressBarLoading;
    @BindView(R.id.progressBarMore)
    protected ProgressBar progressBarMore;
    @BindView(R.id.recyclerViewArticles)
    protected RecyclerView recyclerViewArticles;

    private SearchView mSearchView;
    private Dialog myDialog;
    private ArticleAPI mArticleAPI;
    private ArticleAdapter mAdapter;
    private SearchRequestModel mSearchRequest = new SearchRequestModel();
    private FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        setupSearchViewMenu(searchItem);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mSearchRequest.setmQuery(null);
                search(false);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * setupSearchViewMenu
     *
     * @param searchItem {@link MenuItem}
     */
    private void setupSearchViewMenu(final MenuItem searchItem) {
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchRequest.setmQuery(query);
                search(false);
                mSearchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                FilterDialogFragment filterDialogFragment = FilterDialogFragment.newInstance(mSearchRequest);
                filterDialogFragment.show(mFragmentManager, IntentConstants.DIALOG_FILTER_TAG);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishFilterDialog(SearchRequestModel searchRequestModel) {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        search(false);
    }

    /**
     * Init the views for Layout
     */
    private void initViews() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setUpAPI();
        setUpAdapter();
        setUpWipeRefreshLayout();
        setUpRecyclerView();

        mSearchRequest.resetPage();
    }

    /**
     * setUpAPI
     */
    private void setUpAPI() {
        mArticleAPI = RetrofitUtil.get().create(ArticleAPI.class);
    }

    /**
     * setUpAdapter
     */
    private void setUpAdapter() {
        mAdapter = new ArticleAdapter();
        mAdapter.setLoadingMoreListener(MainActivity.this::searchMore);
    }

    /**
     * setUpWipeRefreshLayout
     */
    private void setUpWipeRefreshLayout() {
        swipeRefreshLayoutMain.setOnRefreshListener(() -> MainActivity.this.search(true));
        swipeRefreshLayoutMain.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    /**
     * setUpRecyclerView
     */
    private void setUpRecyclerView() {
        final int spanCount = ConfigurationUtils.isLandscape(MainActivity.this) ? 4 : 2;
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                spanCount, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewArticles.setLayoutManager(manager);
        recyclerViewArticles.setAdapter(mAdapter);
        recyclerViewArticles.setHasFixedSize(true);
    }

    /**
     * search
     *
     * @param isWipe {@link Boolean}
     */
    private void search(final boolean isWipe) {
        if (!ConfigurationUtils.isNetworkAvailable(MainActivity.this)) {
            dialogMessageForInternetRequest(false);
        } else {
            swipeRefreshLayoutMain.setRefreshing(false);
            if (null != myDialog)
                myDialog.dismiss();
        }

        if (!isWipe) {
            progressBarLoading.setVisibility(View.VISIBLE);
            relativeLayoutLoading.setVisibility(View.VISIBLE);
        }

        fetchArticles(body -> {
            if (body != null && body.getmArticles().size() > 0)
                mAdapter.setArticles(body.getmArticles());
        });
    }

    /**
     * searchMore
     */
    private void searchMore() {
        if (!ConfigurationUtils.isNetworkAvailable(MainActivity.this)) {
            dialogMessageForInternetRequest(true);
        } else {
            swipeRefreshLayoutMain.setRefreshing(false);
            if (null != myDialog)
                myDialog.dismiss();
        }

        progressBarMore.setVisibility(View.VISIBLE);

        mSearchRequest.nextPage();

        fetchArticles(body -> {
            if (body != null && body.getmArticles().size() > 0)
                mAdapter.setMoreArticles(body.getmArticles());
        });
    }

    /**
     * dialogMessageForInternetRequest
     *
     * @param isSearchMore {@link Boolean}
     */
    private void dialogMessageForInternetRequest(final boolean isSearchMore) {
        myDialog = new Dialog(MainActivity.this);
        myDialog.setContentView(R.layout.dialog_connection);
        myDialog.setTitle(getString(R.string.connection_error_title));
        myDialog.setCancelable(false);
        final Button buttonRetry = (Button) myDialog.findViewById(R.id.buttonConnection);
        buttonRetry.setOnClickListener(v -> {
            if (isSearchMore)
                MainActivity.this.searchMore();
            else
                MainActivity.this.search(false);
        });
        myDialog.show();
    }

    /**
     * fetchArticles
     *
     * @param listener {@link LoadingListener}
     */
    private void fetchArticles(final LoadingListener listener) {
        mArticleAPI.getArticles(mSearchRequest.toQueryMap())
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

        if (response.isSuccessful())
            listener.onLoading(response.body());

        swipeRefreshLayoutMain.setRefreshing(false);
        progressBarMore.setVisibility(View.GONE);
        progressBarLoading.setVisibility(View.GONE);
        relativeLayoutLoading.setVisibility(View.GONE);
    }
}
