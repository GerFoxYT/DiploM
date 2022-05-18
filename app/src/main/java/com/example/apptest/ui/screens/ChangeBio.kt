package com.example.apptest.ui.screens

import com.example.apptest.R
import com.example.apptest.dataBase.USER
import com.example.apptest.dataBase.setBioToDataBase
import kotlinx.android.synthetic.main.fragment_change_bio.*


class ChangeBio : BaseChange(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        s_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = s_input_bio.text.toString()
        setBioToDataBase(newBio)


    }


}
