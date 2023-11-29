package com.ridehailing.driver.utils

object FormatterUtils {
    fun formatCurrency(amount: Int): String {
        val formatter = java.text.DecimalFormat("#.###")
        return formatter.format(amount)
    }
}