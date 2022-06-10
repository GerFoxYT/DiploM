package com.example.gerfoxmessages.ui.screens.register

import com.example.gerfoxmessages.dataBase.*
import com.example.gerfoxmessages.ui.screens.base.BaseChange
import com.example.gerfoxmessages.utilits.AppValueEventListener
import com.example.gerfoxmessages.utilits.showToast
import gerfoxmessages.R
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


class ChangeUsername : BaseChange(R.layout.fragment_change_username) {

    lateinit var mNewUsername: String

    override fun onResume() {
        super.onResume()
        s_input_username.setText(USER.username)
    }

    override fun change() {
        mNewUsername = s_input_username.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("Такой пользователь уже существует")
                    } else {
                        changeUsername()
                    }
                })

        }
    }

    private fun changeUsername() {

        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername(mNewUsername)
                }
            }
    }


}

