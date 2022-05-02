package com.example.gerfoxmessage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gerfoxmessage.databinding.FragmentChatsBinding


class Chats : Fragment() {

private lateinit var mBinding: FragmentChatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentChatsBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()

    }
}