package com.cuongnl.ridehailing.enums

import com.cuongnl.ridehailing.R

enum class Language(
    val languageCode: String,
    val languageText: String,
    val icon: Int
) {
    VIETNAMESE("vi", "Tiếng Việt", R.drawable.app_common_assets_images_vietnam_ico_vietnam),
    ENGLISH("en", "English", R.drawable.app_common_assets_images_english_ico_english),
}