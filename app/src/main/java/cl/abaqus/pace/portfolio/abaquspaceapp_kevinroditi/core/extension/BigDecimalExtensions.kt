package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.extension

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale

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
fun BigDecimal.toPercentageString(): String {
    return "%.2f%%".format(this.setScale(2, RoundingMode.HALF_UP))
}

fun Double.toBigDecimalSafe(): BigDecimal =
    BigDecimal.valueOf(this)
