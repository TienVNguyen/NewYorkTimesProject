/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;
import com.training.tiennguyen.newyorktimesproject.listeners.ConnectionDialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link ConnectionDialogFragment}
 *
 * @author TienNguyen
 */
public class ConnectionDialogFragment extends DialogFragment {
    private static boolean mIsSearchMore;
    @BindView(R.id.buttonConnection)
    protected Button mButtonConnection;

    /**
     * Empty constructor is required for {@link DialogFragment}
     */
    public ConnectionDialogFragment() {
    }

    /**
     * newInstance
     *
     * @param isSearchMore {@link Boolean}
     * @param title        {@link String}
     * @return {@link ConnectionDialogFragment}
     */
    public static ConnectionDialogFragment newInstance(final boolean isSearchMore, final String title) {
        mIsSearchMore = isSearchMore;

        final ConnectionDialogFragment frag = new ConnectionDialogFragment();
        final Bundle args = new Bundle();
        args.putString(IntentConstants.DIALOG_CONNECTION_TITLE, title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_connection, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpDialogConfig();
        setUpTryConnection();
    }

    /**
     * setUpDialogConfig
     */
    private void setUpDialogConfig() {
        getDialog().setTitle(
                getArguments().getString(
                        IntentConstants.DIALOG_CONNECTION_TITLE,
                        getString(R.string.connection_error_title)));
        getDialog().setCancelable(false);

        final Window window = getDialog().getWindow();
        if (null != window)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     * setUpTryConnection
     */
    private void setUpTryConnection() {
        mButtonConnection.setOnClickListener(view1 -> onConnectionDialogDismiss()
        );
    }

    /**
     * onConnectionDialogDismiss
     */
    private void onConnectionDialogDismiss() {
        final ConnectionDialogListener listener = (ConnectionDialogListener) getActivity();
        listener.onFinishConnectionDialog(mIsSearchMore);
        this.dismiss();
    }
}
