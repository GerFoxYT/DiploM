package com.example.apptest.ui.screens.base

import androidx.fragment.app.Fragment
import com.example.apptest.utilits.APP_ACTIVITY

open class Base(val layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}


