package com.example.rotah

import android.annotation.SuppressLint
import java.util.*


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

import android.widget.Button

import androidx.core.util.Pair
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.RangeDateSelector

@SuppressLint("RestrictedApi")
class MainActivity2 : AppCompatActivity() {
    private lateinit var datePicker: MaterialDatePicker<Pair<Long, Long>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val dateRangeButton = findViewById<Button>(R.id.dateRangeButton)
        dateRangeButton.setOnClickListener {
            showCustomDatePicker()
        }
    }

    private fun showCustomDatePicker() {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.timeInMillis = today
        // Set the start date as January 1st of the current year
        calendar.set(Calendar.MONTH, Calendar.JANUARY)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startOfYear = calendar.timeInMillis
        // Set the end date as the last day of the current month
        calendar.timeInMillis = today
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val endOfMonth = calendar.timeInMillis
        val constraintsBuilder = CalendarConstraints.Builder()
            .setStart(startOfYear)
            .setEnd(endOfMonth)
            .setOpenAt(endOfMonth)
            .setValidator(DateValidatorPointBackward.now())
        datePicker = MaterialDatePicker()
        val selector = CustomOnSelectionChangedListener(supportFragmentManager, datePicker)
        datePicker = MaterialDatePicker.Builder.customDatePicker(selector)
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        datePicker.show(supportFragmentManager, "DATE_RANGE_PICKER")
        datePicker.addOnPositiveButtonClickListener { selection ->
            // Tangkap tanggal yang dipilih
            val startDate = selection.first
            val endDate = selection.second

            // Lakukan sesuatu dengan tanggal yang dipilih

            // Tutup DatePicker setelah selesai
            datePicker.dismiss()
        }
    }
}