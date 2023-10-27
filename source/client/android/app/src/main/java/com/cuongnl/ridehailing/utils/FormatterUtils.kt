package com.cuongnl.ridehailing.utils

import com.google.i18n.phonenumbers.PhoneNumberUtil

object FormatterUtils {
    fun formatPhoneNumberToInternationalFormation(phoneNumber: String, countryCode: String): String {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val parsedNumber = phoneNumberUtil.parse(phoneNumber, countryCode)
            return phoneNumberUtil.format(
                parsedNumber,
                PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return phoneNumber
    }
}