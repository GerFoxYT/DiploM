package com.example.apptest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.apptest.activites.RegisterActivity
import com.example.apptest.databinding.ActivityMainBinding
import com.example.apptest.ui.fragments.Chats
import com.example.apptest.ui.objects.AppDrawer
import com.example.apptest.utilits.AUTH
import com.example.apptest.utilits.initFirebase
import com.example.apptest.utilits.replaceActivity
import com.example.apptest.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
     lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }



    private fun initFunc() {
        if (AUTH.currentUser!=null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(Chats(),false)
        } else {
            replaceActivity(RegisterActivity())
        }

    }

    private fun initFields() {
        mToolbar = mBinding.mToolBar
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()

    }

}


