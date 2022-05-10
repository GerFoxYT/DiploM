package com.example.apptest.ui.fragments

import androidx.fragment.app.Fragment
import com.example.apptest.R
import com.example.apptest.utilits.APP_ACTIVITY


class Chats : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
APP_ACTIVITY.title = "Чаты"
    }
}