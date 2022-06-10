package com.example.gerfoxmessages.ui.screens.base

import androidx.fragment.app.Fragment
import com.example.gerfoxmessages.utilits.APP_ACTIVITY

open class Base(val layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}


