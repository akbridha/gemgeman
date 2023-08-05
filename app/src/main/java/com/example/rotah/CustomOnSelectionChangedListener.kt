package com.example.rotah

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Parcel
import java.util.*
import androidx.core.util.Pair
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.*
import java.util.concurrent.TimeUnit


@SuppressLint("RestrictedApi")
class CustomOnSelectionChangedListener(
    private val fragmentManager: FragmentManager,
    private var datePicker: MaterialDatePicker<Pair<Long, Long>>?
) : RangeDateSelector() {

    private var first = true
//    private var firstDate: Long? = null

//    override fun select(selection: Long) {
//        if (first || selection < firstDate!!) {
//            super.select(selection)
//            super.select(selection)
//            firstDate = selection
//            onSelectionOfFirstDate(firstDate)
//            first = false
//        } else {
//            super.select(firstDate!!)
//            super.select(selection)
//            if (selection != firstDate) {
//                first = true
//            }
//        }
//    }


//    private fun onSelectionOfFirstDate(firstDate: Long?) {
//        val calendar1 = Calendar.getInstance()
//        calendar1.timeInMillis = firstDate!!
//
//        // Add six days to the first date selection
//        calendar1.add(Calendar.DAY_OF_MONTH, 6)
//
//        val maxDate = calendar1.timeInMillis
//        val today = MaterialDatePicker.todayInUtcMilliseconds()
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//        calendar.timeInMillis = today
//        // Set the start date as January 1st of the current year
//        calendar.set(Calendar.MONTH, Calendar.JANUARY)
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        val startOfYear = calendar.timeInMillis
//        // Set the end date as the last day of the current month
//        calendar.timeInMillis = today
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
//        val endOfMonth = calendar.timeInMillis
//        val constraintsBuilder = CalendarConstraints.Builder()
//            .setStart(startOfYear)
//            .setEnd(endOfMonth)
//            .setOpenAt(endOfMonth)
//            .setValidator(DateValidatorPointBackward.before(maxDate))
//            .build()
//        val newDatePicker =
//            MaterialDatePicker.Builder.customDatePicker(this@CustomOnSelectionChangedListener)
//                .setCalendarConstraints(constraintsBuilder).build()
//        //datePicker = newDatePicker
//        Handler().post {
//            if (datePicker!!.isVisible) {
//                datePicker!!.dismiss()
//            }
//            datePicker = newDatePicker
//        }
//        datePicker = newDatePicker
//
//        datePicker!!.show(fragmentManager, "Select Range")
//
//    }



    private var firstDate: Long? = null

    override fun select(selection: Long) {
        if (firstDate == null) {
            firstDate = selection
            onSelectionOfFirstDate(firstDate)
        } else {
            super.select(firstDate!!)
            super.select(selection)
            firstDate = null
        }
    }

    private fun onSelectionOfFirstDate(firstDate: Long?) {
        val endDate = firstDate!! + TimeUnit.DAYS.toMillis(6)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = endDate
        val maxDate = calendar.timeInMillis

        val constraintsBuilder = CalendarConstraints.Builder()
            .setStart(firstDate)
            .setEnd(maxDate)
            .setOpenAt(firstDate)
            .setValidator(DateValidatorPointForward.from(firstDate))
            .build()

        val newDatePicker = MaterialDatePicker.Builder.customDatePicker(this)
            .setCalendarConstraints(constraintsBuilder)
            .build()

        if (datePicker != null && datePicker!!.isVisible) {
            datePicker!!.dismiss()
        }

        datePicker = newDatePicker
        datePicker!!.show(fragmentManager, "DATE_PICKER")
    }
}