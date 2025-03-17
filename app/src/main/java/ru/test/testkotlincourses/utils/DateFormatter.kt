package ru.test.testkotlincourses.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


    fun String.formatDate():String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(this)

        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }

        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val monthName = when (month) {
            1 -> "Января"
            2 -> "Февраля"
            3 -> "Марта"
            4 -> "Апреля"
            5 -> "Мая"
            6 -> "Июня"
            7 -> "Июля"
            8 -> "Августа"
            9 -> "Сентября"
            10 -> "Октября"
            11 -> "Ноября"
            12 -> "Декабря"
            else -> ""
        }

        return "$dayOfMonth $monthName $year"
    }
