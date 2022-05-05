package com.example.apptest.ui.fragments

import com.example.apptest.R
import com.example.apptest.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBio : BaseChange(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        s_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = s_input_bio.text.toString()
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_BIO).setValue(newBio)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Данные обновленны")
                    USER.bio = newBio
                    fragmentManager?.popBackStack()
                }
            }
    }
}
