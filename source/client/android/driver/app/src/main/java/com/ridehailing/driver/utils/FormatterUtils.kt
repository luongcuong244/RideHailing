package com.ridehailing.driver.utils

object FormatterUtils {
    fun formatCurrency(amount: Int): String {
        val formatter = java.text.DecimalFormat("#.###", java.text.DecimalFormatSymbols.getInstance(java.util.Locale.US))
        return formatter.format(amount)
    }
}