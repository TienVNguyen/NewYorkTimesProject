/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.listeners;

import com.training.tiennguyen.newyorktimesproject.models.SearchResultModel;

/**
 * {@link ResultListener}
 *
 * @author TienVNguyen
 */
public interface ResultListener {
    void onResult(SearchResultModel body);
}
