package com.example.ratulaggarwal.schedulemessage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by ratulaggarwal on 9/10/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public static int my_year, my_month, my_day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // save date in an object.
        my_year = year;
        my_month = monthOfYear;
        my_day = dayOfMonth;
    }
}
