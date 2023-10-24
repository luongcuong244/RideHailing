package com.cuongnl.ridehailing.activity_behavior

interface ILoginActivityBehavior {
    fun isInvalidTextVisible(): Boolean
    fun canClickContinueButton(): Boolean
    fun openPolicy()
    fun onContinueButtonClicked()
}