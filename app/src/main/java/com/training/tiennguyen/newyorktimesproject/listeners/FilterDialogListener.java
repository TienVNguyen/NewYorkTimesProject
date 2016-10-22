/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.listeners;

import com.training.tiennguyen.newyorktimesproject.models.SearchRequestModel;

/**
 * {@link FilterDialogListener}
 *
 * @author TienVNguyen
 */
public interface FilterDialogListener {

    /**
     * onFinishFilterDialog
     *
     * @param searchRequestModel SearchRequestModel
     */
    void onFinishFilterDialog(final SearchRequestModel searchRequestModel);
}
