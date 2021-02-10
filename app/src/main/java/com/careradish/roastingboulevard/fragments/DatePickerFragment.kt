package com.careradish.roastingboulevard.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.careradish.roastingboulevard.R
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val style =  R.style.SpinnerDatePickerDialog
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        var dialog=DatePickerDialog(activity!!, style, this, year, month, day)
        c.add(Calendar.YEAR, 0)
        c.add(Calendar.MONTH, 1)
        dialog.datePicker.minDate = c.timeInMillis;
        c.add(Calendar.YEAR, 4)
        dialog.datePicker.maxDate = c.timeInMillis;

        hideDayPicker(dialog)

        return dialog
    }

    private fun hideDayPicker(dialog: DatePickerDialog) {
        (dialog.datePicker as ViewGroup).findViewById<View>(
            Resources.getSystem().getIdentifier("day", "id", "android")
        ).visibility = View.GONE
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            dateSet?.invoke("$month-$year")
    }

    var dateSet: ((dateResult:String) -> Unit)? = null
}