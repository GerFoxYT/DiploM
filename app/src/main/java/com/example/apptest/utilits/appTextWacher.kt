package com.example.apptest.utilits

import android.text.Editable
import android.text.TextWatcher

class appTextWacher(val onSuccess: (Editable?) -> Unit) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(s: Editable?) {
        onSuccess(s)
    }
}