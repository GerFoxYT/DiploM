package com.example.apptest.ui.fragments

import androidx.fragment.app.Fragment
import com.example.apptest.R
import com.example.apptest.utilits.APP_ACTIVITY
import com.example.apptest.utilits.hideKeyboard


class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
APP_ACTIVITY.title = ("GerFox Message")
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }
}