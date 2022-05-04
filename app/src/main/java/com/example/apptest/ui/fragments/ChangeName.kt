package com.example.apptest.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.apptest.MainActivity
import com.example.apptest.R
import com.example.apptest.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeName : Fragment(R.layout.fragment_change_name) {


    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.s_confirm_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.s_confirm_change -> changeName()
        }
            return true

    }

    private fun changeName() {
        val name = s_input_name.text.toString()
        val surname = s_input_surname.text.toString()
        if (name.isEmpty()){
            showToast("Имя не может быть пустым!")
        } else {
            val fullname = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULLNAME)
            .setValue(fullname).addOnCompleteListener {
                if (it.isSuccessful){
                    showToast("Данные обновленны!")
                    USER.fullname = fullname
                    fragmentManager?.popBackStack()
                }
                }

        }
    }
}
