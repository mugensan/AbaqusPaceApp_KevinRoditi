package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.extension

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale
import java.util.RoundingMode

/**
 * Formatting BigDecimal as currency String (USD)
 */
fun BigDecimal.formatAsCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this)
}

/**
 * Formatting BigDecimal as percentage String with 2 decimal
 */
fun BigDecimal.toPercentageString(): String{
    return "%,2f%%".format(this)
}

fun Double.toBigDecimalSafe(): BigDecimal =
    BigDecimal.valueOf(this)