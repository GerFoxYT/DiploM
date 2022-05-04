package com.example.apptest.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.apptest.MainActivity
import com.example.apptest.R
import com.example.apptest.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeName : BaseChange(R.layout.fragment_change_name) {


    override fun onResume() {
        super.onResume()
        initFillnameList()
    }

    private fun initFillnameList() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            s_input_name.setText(fullnameList[0])
            s_input_surname.setText(fullnameList[1])
        } else s_input_name.setText(fullnameList[0])
    }

    override fun change() {
        val name = s_input_name.text.toString()
        val surname = s_input_surname.text.toString()
        if (name.isEmpty()) {
            showToast("Имя не может быть пустым!")
        } else {
            val fullname = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME)
                .setValue(fullname).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Данные обновленны!")
                        USER.fullname = fullname
                        fragmentManager?.popBackStack()
                    }
                }

        }
    }
}
