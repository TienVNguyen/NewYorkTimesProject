/*
 * Copyright (c) 2016. Self Training Systems, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by TienVNguyen <tien.workinfo@gmail.com - tien.workinfo@icloud.com>, October 2015
 */

package com.training.tiennguyen.newyorktimesproject.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;
import com.training.tiennguyen.newyorktimesproject.listeners.FilterDialogListener;
import com.training.tiennguyen.newyorktimesproject.models.SearchRequestModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * {@link FilterDialogFragment}
 *
 * @author TienVNguyen
 */
public class FilterDialogFragment extends DialogFragment {
    private EditText mEditText;
    private Spinner mSpinnerSortOrder;
    private CheckedTextView mCheckedTextViewArts, mCheckedTextViewFashionStyle, mCheckedTextViewSports;
    private Button mButtonSave;
    private DatePickerDialog mDatePickerDialog;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private static SearchRequestModel mSearchRequestModel;

    /**
     * Empty constructor is required for {@link DialogFragment}
     */
    public FilterDialogFragment() {
    }

    /**
     * newInstance
     *
     * @param searchRequestModel {@link SearchRequestModel}
     * @return {@link FilterDialogFragment}
     */
    public static FilterDialogFragment newInstance(final SearchRequestModel searchRequestModel) {
        mSearchRequestModel = searchRequestModel;

        final FilterDialogFragment frag = new FilterDialogFragment();
        final Bundle args = new Bundle();
        args.putString(IntentConstants.DIALOG_FILTER_TITLE, "Title");
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_filter, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditText = (EditText) view.findViewById(R.id.editTaskStartedSelect);
        mSpinnerSortOrder = (Spinner) view.findViewById(R.id.spinnerSortOrder);
        mCheckedTextViewArts = (CheckedTextView) view.findViewById(R.id.checkedTextViewArts);
        mCheckedTextViewFashionStyle = (CheckedTextView) view.findViewById(R.id.checkedTextViewFashionStyle);
        mCheckedTextViewSports = (CheckedTextView) view.findViewById(R.id.checkedTextViewSports);
        mButtonSave = (Button) view.findViewById(R.id.buttonSave);

        //setUpDatePicker();
        setUpSpinner();
        mButtonSave.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_ACTION_DONE == actionId) {
                    FilterDialogListener listener = (FilterDialogListener) getActivity();
                    listener.onFinishFilterDialog(mSearchRequestModel);
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        getDialog().setTitle(getArguments()
                .getString(IntentConstants.DIALOG_FILTER_TITLE, getString(R.string.text_advanced_search_filters)));

        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     * setUpDatePicker
     */
    private void setUpDatePicker() {
        final Date currentDate = new Date();
        final Date startedDate = new Date(); //taskModel.getmStartedDate();TODO

        mEditText.setText(mSimpleDateFormat.format(startedDate));
        mEditText.setInputType(InputType.TYPE_NULL);
        mEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                mDatePickerDialog.show();
            v.clearFocus();
        });


        final Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            final Calendar newDate = Calendar.getInstance();
            newDate.set(year, month, dayOfMonth);
            mEditText.setText(mSimpleDateFormat.format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMinDate(currentDate.getTime());

    }

    /**
     * setUpSpinner
     */
    private void setUpSpinner() {
        final ArrayAdapter<CharSequence> adapterPriority = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_sort_order, android.R.layout.simple_spinner_item);
        adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //mSpinnerSortOrder.setSelection(adapterPriority.getPosition(taskModel.getmPriority()));TODO
        mSpinnerSortOrder.refreshDrawableState();
    }

}
