package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R

enum class NumberPhone(
    val countryFlag: Int,
    val countryName: String,
    val phoneCode: String,
    val minLength: Int = 1,
    val maxLength: Int = 50
) {
    VIET_NAM(R.drawable.flag_vietnam, "Việt Nam", "+84", 10, 10),
    VIET_NAM1(R.drawable.flag_vietnam, "Việt Nam", "+85", 10, 10),
    VIET_NAM2(R.drawable.flag_vietnam, "Việt Nam", "+86", 10, 10),
    VIET_NAM3(R.drawable.flag_vietnam, "Việt Nam", "+87", 10, 10),
    VIET_NAM4(R.drawable.flag_vietnam, "Việt Nam", "+88", 10, 10),
    VIET_NAM5(R.drawable.flag_vietnam, "Việt Nam", "+89", 10, 10),
    VIET_NAM6(R.drawable.flag_vietnam, "Việt Nam", "+90", 10, 10),
    VIET_NAM7(R.drawable.flag_vietnam, "Việt Nam", "+9800", 10, 10),
    VIET_NAM8(R.drawable.flag_vietnam, "Việt Nam", "+84", 10, 10),
    VIET_NAM9(R.drawable.flag_vietnam, "Việt Nam", "+84", 10, 10),
    VIET_NAM10(R.drawable.flag_vietnam, "Việt Nam", "+84", 10, 10),
    VIET_NAM11(R.drawable.flag_vietnam, "Việt Nam", "+84", 10, 10),
}