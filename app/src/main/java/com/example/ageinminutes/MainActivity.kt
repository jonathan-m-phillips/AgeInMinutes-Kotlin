package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view->
            clickDatePicker(view)
        }


    }

    fun clickDatePicker(view: View) {

        // creating variable for Calendar object
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        // opens popup for date picker
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->

                // Toast.makeText(this,
                    // "The chosen year is $selectedYear, the month is $selectedMonth, and the day is $selectedDayOfMonth",
                    // Toast.LENGTH_LONG).show()

                // adding 1 to month because months start at 0 in Kotlin
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                // setting the text to the ID of tvSelectedDate
                tvSelectedDate.setText(selectedDate)

                // SimpleDateFormat class to give date in string
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                // giving theDate type Date - converted from string to Date object format
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

            }
            ,year
            ,month
            ,day)

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}