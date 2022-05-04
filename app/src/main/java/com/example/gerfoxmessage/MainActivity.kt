package com.example.gerfoxmessage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.gerfoxmessage.activites.RegisterActivity
import com.example.gerfoxmessage.databinding.ActivityMainBinding
import com.example.gerfoxmessage.ui.fragments.Chats
import com.example.gerfoxmessage.ui.objects.AppDrawer
import com.example.gerfoxmessage.utilits.AUTH
import com.example.gerfoxmessage.utilits.initFirebase
import com.example.gerfoxmessage.utilits.replaceActivity
import com.example.gerfoxmessage.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar
    private lateinit var mAppDrawer: AppDrawer

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


