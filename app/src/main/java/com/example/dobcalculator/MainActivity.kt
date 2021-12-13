package com.example.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateTextView: TextView? = null
    private var ageInMinutesTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Finding Text View
        this.selectedDateTextView = findViewById(R.id.selectedDateTextView)
        this.ageInMinutesTextView = findViewById(R.id.ageInMinutesTextView)

        val dateButtonPicker: Button = findViewById(R.id.datePicketButton)
        dateButtonPicker.setOnClickListener {
            clickDatePicker()
        }
    }

    // DATE PICKER FUNCTION
    private fun clickDatePicker() {
        Toast.makeText(this, "Date Picker Pressed", Toast.LENGTH_LONG).show() // TOAST is not showing up. Not sure why

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val monthOfDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDate ->
            // Month starts from 0
            this.calculateAgeInMinutes(selectedYear, selectedMonth, selectedDate)
        }, year, month, monthOfDay)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }

    private fun calculateAgeInMinutes(year: Int, month: Int, date: Int) {
        val monthToDisplay = month + 1
        val selectedDate = "$date/$monthToDisplay/$year"
        selectedDateTextView?.text = selectedDate

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val dateObject = sdf.parse(selectedDate)

        val selectedDateInMinutes = dateObject.time / 60000
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())).time / 60000
        val differenceInMinutes = currentDate - selectedDateInMinutes
        this.ageInMinutesTextView?.text = differenceInMinutes.toString()
    }
}