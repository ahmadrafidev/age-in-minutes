package dev.ahmadrafi.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.buttonDate)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
                    view, selectedYear, selectedMonth, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
            val simpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = simpleDate.parse(selectedDate)
            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = simpleDate.parse(simpleDate.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time / 60000
            val differencesInMinutes = currentDateInMinutes - selectedDateInMinutes
            tvAgeInMinutes?.text = differencesInMinutes.toString()
            },
                year, month, day
        ).show()
    }
}