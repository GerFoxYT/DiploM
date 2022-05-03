package com.example.gerfoxmessage.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.gerfoxmessage.R
import com.example.gerfoxmessage.activites.RegisterActivity
import com.example.gerfoxmessage.utilits.AUTH
import com.example.gerfoxmessage.utilits.replaceActivity

class Settings : Base(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.s_btn_exit -> {
                AUTH.signOut()
                (activity as RegisterActivity).replaceActivity(RegisterActivity())
            }
        }
        return true
    }
}