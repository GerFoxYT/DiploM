package com.example.gerfoxmessages.ui.screens.register

import androidx.fragment.app.Fragment
import com.example.gerfoxmessages.dataBase.AUTH
import com.example.gerfoxmessages.utilits.APP_ACTIVITY
import com.example.gerfoxmessages.utilits.replaceFragment
import com.example.gerfoxmessages.utilits.restartActivity
import com.example.gerfoxmessages.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import gerfoxmessages.R
import kotlinx.android.synthetic.main.fragment_enter_phone_num.*
import java.util.concurrent.TimeUnit


class EnterPhoneNum : Fragment(R.layout.fragment_enter_phone_num) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener { sendCode() }
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Вход успшено выполнен")
                        restartActivity()
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCode(mPhoneNumber, id))
            }
        }
        register_btn_next.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (register_input_number.text.toString().isEmpty()) {
            showToast("Код не может быть пустым!")

        } else {
            authUser()
        }

    }

    private fun authUser() {
        mPhoneNumber = register_input_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber, 60, TimeUnit.SECONDS, APP_ACTIVITY, mCallBack
        )
    }

}
