package com.example.gerfoxmessage.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.gerfoxmessage.R
import com.example.gerfoxmessage.databinding.FragmentRegisterActivityBinding
import com.example.gerfoxmessage.ui.fragments.EnterPhoneNum
import com.example.gerfoxmessage.utilits.initFirebase
import com.example.gerfoxmessage.utilits.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

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
        mToolbar = mBinding.registerToolBar
        setSupportActionBar(mToolbar)
        title = getString(R.string.title_ur_phone)

        replaceFragment(EnterPhoneNum(), false)

    }
}

