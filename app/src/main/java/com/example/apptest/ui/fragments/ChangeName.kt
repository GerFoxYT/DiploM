package com.example.apptest.ui.fragments

import com.example.apptest.R
import com.example.apptest.utilits.USER
import com.example.apptest.utilits.setNameToDataBase
import com.example.apptest.utilits.showToast
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeName : BaseChange(R.layout.fragment_change_name) {


    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    private fun initFullnameList() {
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
            showToast("Имя не может быть пустным")
        } else {
            val fullname = "$name $surname"
            setNameToDataBase(fullname)

        }
    }


}