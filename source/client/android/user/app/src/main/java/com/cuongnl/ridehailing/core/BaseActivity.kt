package com.cuongnl.ridehailing.core

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.extensions.setAppLocale
import com.cuongnl.ridehailing.utils.Constant
import com.cuongnl.ridehailing.utils.LocalStorageUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    override fun attachBaseContext(newBase: Context) {
        val languageCode = LocalStorageUtils.readData(newBase, LocalStorageUtils.KEY_LANGUAGE) as String?
        if (languageCode == null) {
            super.attachBaseContext(ContextWrapper(newBase))
        } else {
            super.attachBaseContext(ContextWrapper(newBase.setAppLocale(languageCode)))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}