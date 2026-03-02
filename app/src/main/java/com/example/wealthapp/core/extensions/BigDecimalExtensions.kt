package com.example.wealthapp.core.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

/**
 * Extension functions for BigDecimal to handle financial formatting.
 */

fun BigDecimal.formatAsCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this)
}

fun BigDecimal.toPercentageString(): String {
    return "%.2f%%".format(this)
}

fun String.toBigDecimalSafe(): BigDecimal {
    return try {
        BigDecimal(this)
    } catch (e: Exception) {
        BigDecimal.ZERO
    }
}
