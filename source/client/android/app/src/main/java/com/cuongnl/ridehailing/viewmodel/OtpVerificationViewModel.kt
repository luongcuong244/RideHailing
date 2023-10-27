package com.cuongnl.ridehailing.viewmodel

import android.app.Activity
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.firebase.auth.FirebasePhoneNumberAuth
import com.cuongnl.ridehailing.utils.Constant
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpVerificationViewModel : ViewModel() {

    private var _internationalPhoneNumber = mutableStateOf("")
    private var _otpTimeoutValue = mutableLongStateOf(Constant.OTP_TIMEOUT_IN_SECOND);

    val internationalPhoneNumber : State<String> = _internationalPhoneNumber
    val otpTimeoutValue : State<Long> = _otpTimeoutValue

    private var otpCountdown : CountDownTimer? = null
    private var otpId : String? = null

    fun initiateOtp(activity: Activity) {

        if(internationalPhoneNumber.value.isEmpty()) {
            throw Exception("Phone number can't be empty")
        }

        FirebasePhoneNumberAuth.verifyPhoneNumber(activity, internationalPhoneNumber.value, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                otpId = p0
                Log.d("DSDSD", "onCodeSent")
            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("DSDSD", "onVerificationCompleted: ${p0.smsCode}")
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.d("DSDSD", "onVerificationFailed")
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                Log.d("DSDSD", "onCodeAutoRetrievalTimeOut: ${p0}")
            }
        })
    }

    fun verifyOtp(otp: String) {
        if(otpId != null){
            val credential = PhoneAuthProvider.getCredential(otpId!!, otp)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.d("DSDSD", "verifyOtp: success")
                    } else {
                        Log.d("DSDSD", "verifyOtp: fail ${it.exception?.message}")
                    }
                }
        }
    }

    fun startOtpTimer() {

        setOtpTimeoutValue(Constant.OTP_TIMEOUT_IN_SECOND)

        otpCountdown = object : CountDownTimer(otpTimeoutValue.value, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                setOtpTimeoutValue(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                setOtpTimeoutValue(0L)
            }
        }
    }

    fun setInternationalPhoneNumber(phoneNumber: String) {
        _internationalPhoneNumber.value = phoneNumber
    }

    fun setOtpTimeoutValue(newValue: Long) {
        _otpTimeoutValue.longValue = newValue
    }
}