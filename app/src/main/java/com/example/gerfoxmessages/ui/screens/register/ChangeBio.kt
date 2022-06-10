package com.example.gerfoxmessages.ui.screens.register

import com.example.gerfoxmessages.dataBase.USER
import com.example.gerfoxmessages.dataBase.setBioToDataBase
import com.example.gerfoxmessages.ui.screens.base.BaseChange
import gerfoxmessages.R
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
