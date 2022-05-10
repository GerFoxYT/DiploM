package com.example.apptest.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.apptest.R
import com.example.apptest.databinding.FragmentRegisterActivityBinding
import com.example.apptest.ui.fragments.EnterPhoneNum
import com.example.apptest.utilits.initFirebase
import com.example.apptest.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: FragmentRegisterActivityBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = FragmentRegisterActivityBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = "Ваш телефон"

        replaceFragment(EnterPhoneNum(), false)

    }
}

