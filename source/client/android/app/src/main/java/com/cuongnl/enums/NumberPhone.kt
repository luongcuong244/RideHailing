package com.cuongnl.enums

import com.cuongnl.ridehailing.R

enum class NumberPhone(
    val countryFlag: Int,
    val phoneCode: String,
    val minLength: Int = 1,
    val maxLength: Int = 50
) {
    VIET_NAM(R.drawable.flag_vietnam, "+84", 10, 10)
}