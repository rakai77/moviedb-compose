package com.example.common_ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.dateConvert() : String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    return try {
        val date: Date = inputFormat.parse(this) ?: return this
        outputFormat.format(date)
    } catch (e: Exception) {
        this
    }
}