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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.training.tiennguyen.newyorktimesproject.R;
import com.training.tiennguyen.newyorktimesproject.constants.DataConstants;
import com.training.tiennguyen.newyorktimesproject.constants.IntentConstants;
import com.training.tiennguyen.newyorktimesproject.listeners.FilterDialogListener;
import com.training.tiennguyen.newyorktimesproject.models.SearchRequestModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link FilterDialogFragment}
 *
 * @author TienVNguyen
 */
public class FilterDialogFragment extends DialogFragment {
    private static SearchRequestModel mSearchRequestModel;
    @BindView(R.id.editTaskStartedSelect)
    protected EditText mEditText;
    @BindView(R.id.spinnerSortOrder)
    protected Spinner mSpinnerSortOrder;
    @BindView(R.id.checkedTextViewArts)
    protected CheckBox mCheckedTextViewArts;
    @BindView(R.id.checkedTextViewFashionStyle)
    protected CheckBox mCheckedTextViewFashionStyle;
    @BindView(R.id.checkedTextViewSports)
    protected CheckBox mCheckedTextViewSports;
    @BindView(R.id.buttonSave)
    protected Button mButtonSave;
    private DatePickerDialog mDatePickerDialog;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(DataConstants.DATE_FORMAT, Locale.getDefault());

    /**
     * Empty constructor is required for {@link DialogFragment}
     */
    public FilterDialogFragment() {
    }

    /**
     * newInstance
     *
     * @param requestModel {@link SearchRequestModel}
     * @param title        {@link String}
     * @return {@link FilterDialogFragment}
     */
    public static FilterDialogFragment newInstance(final SearchRequestModel requestModel, final String title) {
        mSearchRequestModel = requestModel;

        final FilterDialogFragment frag = new FilterDialogFragment();
        final Bundle args = new Bundle();
        args.putString(IntentConstants.DIALOG_FILTER_TITLE, title);
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_filter, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpDialogConfig();
        setUpDatePicker();
        setUpSpinner();
        setUpCheckbox();
        setUpSaveButton();
    }

    /**
     * setUpDialogConfig
     */
    private void setUpDialogConfig() {
        getDialog().setTitle(
                getArguments().getString(
                        IntentConstants.DIALOG_FILTER_TITLE,
                        getString(R.string.text_advanced_search_filters)));
        final Window window = getDialog().getWindow();
        if (null != window)
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     * setUpSaveButton
     */
    private void setUpSaveButton() {
        mButtonSave.setOnClickListener(view1 -> {
                    onSaveRequest();
                    onSaveDialogDismiss();
                }
        );
    }

    /**
     * onSaveRequest
     */
    private void onSaveRequest() {
        mSearchRequestModel.setmBeginDate(mEditText.getText().toString());
        mSearchRequestModel.setmSort(mSpinnerSortOrder.getSelectedItem().toString());
        mSearchRequestModel.setmArts(mCheckedTextViewArts.isChecked());
        mSearchRequestModel.setmFashionStyle(mCheckedTextViewFashionStyle.isChecked());
        mSearchRequestModel.setmSports(mCheckedTextViewSports.isChecked());
    }

    /**
     * onSaveDialogDismiss
     */
    private void onSaveDialogDismiss() {
        final FilterDialogListener listener = (FilterDialogListener) getActivity();
        listener.onFinishFilterDialog(mSearchRequestModel);
        this.dismiss();
    }

    /**
     * setUpDatePicker
     */
    private void setUpDatePicker() {
        final Date currentDate = new Date();
        final Calendar newCalendar = Calendar.getInstance();
        final String beginDate = mSearchRequestModel.getmBeginDate();

        if (null != beginDate && 0 < beginDate.length()) {
            try {
                final Date startedDate = mSimpleDateFormat.parse(beginDate);
                mEditText.setText(mSimpleDateFormat.format(startedDate));
            } catch (ParseException e) {
                Log.e("ERROR_PARSE_DIALOG", e.getMessage());
            }
        }

        mEditText.setInputType(InputType.TYPE_NULL);
        mEditText.setOnClickListener(view -> mDatePickerDialog.show());
        mEditText.setOnLongClickListener(view -> {
            mEditText.setText("");
            return false;
        });

        mDatePickerDialog = new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
            final Calendar newDate = Calendar.getInstance();
            newDate.set(year, month, dayOfMonth);
            mEditText.setText(mSimpleDateFormat.format(newDate.getTime()));
            mDatePickerDialog.dismiss();
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(currentDate.getTime());
    }

    /**
     * setUpSpinner
     */
    private void setUpSpinner() {
        final ArrayAdapter<CharSequence> orderAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_sort_order, android.R.layout.simple_spinner_item);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSortOrder.setAdapter(orderAdapter);
        mSpinnerSortOrder.setSelection(orderAdapter.getPosition(mSearchRequestModel.getmSort()));
        mSpinnerSortOrder.refreshDrawableState();
    }

    /**
     * setUpCheckbox
     */
    private void setUpCheckbox() {
        mCheckedTextViewArts.setChecked(mSearchRequestModel.ismArts());
        mCheckedTextViewFashionStyle.setChecked(mSearchRequestModel.ismFashionStyle());
        mCheckedTextViewSports.setChecked(mSearchRequestModel.ismSports());
    }
}
