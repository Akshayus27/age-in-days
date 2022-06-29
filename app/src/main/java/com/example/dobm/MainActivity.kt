package com.example.dobm

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInHours: TextView? = null
    private val hoursTime = 3600000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateButton: Button = findViewById(R.id.datePickerButton)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)

        dateButton.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, dayOfMonth ->
                Toast.makeText(
                    this,
                    "Date selected: $dayOfMonth/${selectedMonth + 1}/$selectedYear",
                    Toast.LENGTH_SHORT
                ).show()

                val selectedDate = "$dayOfMonth/${selectedMonth + 1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val actualDate = simpleDateFormat.parse(selectedDate)
                val currentDate =
                    simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))


                actualDate?.let {
                    val selectedDateInHours = actualDate.time / hoursTime

                    currentDate?.let {
                        val currentDateInHours = currentDate.time / hoursTime
                        val ageInHours = currentDateInHours - selectedDateInHours
                        tvAgeInHours?.text = ageInHours.toString()
                    }
                }
            }, year, month, day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()

    }
}