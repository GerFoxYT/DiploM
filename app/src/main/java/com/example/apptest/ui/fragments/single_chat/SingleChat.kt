package com.example.apptest.ui.fragments.single_chat

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.models.CommonModel
import com.example.apptest.models.UserModel
import com.example.apptest.ui.fragments.Base
import com.example.apptest.ui.objects.AppValueEventListener
import com.example.apptest.utilits.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_single_chat.*
import kotlinx.android.synthetic.main.toolbar_info.view.*

class SingleChat(private val contact: CommonModel) : Base(R.layout.fragment_single_chat) {

    private lateinit var mListenerInfoToolBar: AppValueEventListener
    private lateinit var mReceivingUser: UserModel
    private lateinit var mToolbarInfo: View
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mRefMessages: DatabaseReference
    private lateinit var mAdapter: SingleChatAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMessagesListener: AppValueEventListener
    private  var mListMessages = emptyList<CommonModel>()

    override fun onResume() {
        super.onResume()
        initToolbar()
        initRecycleView()
    }

    private fun initRecycleView() {
        mRecyclerView = chat_recycle_view
        mAdapter = SingleChatAdapter()
        mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID).child(contact.id)
mRecyclerView.adapter = mAdapter
        mMessagesListener = AppValueEventListener { dataSnapshot ->
            mListMessages = dataSnapshot.children.map { it.getCommonModel() }
            mAdapter.setList(mListMessages)
            mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefMessages.addValueEventListener(mMessagesListener)
    }

    private fun initToolbar() {
        mToolbarInfo = APP_ACTIVITY.mToolbar.toolbar_info
        mToolbarInfo.visibility = View.VISIBLE
        mListenerInfoToolBar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initInfoToolbar()
        }
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolBar)
        chat_btn_sent_message.setOnClickListener {
            val message = chat_input_message.text.toString()
            if (message.isEmpty()) {
            } else sendMessage(message, contact.id, TYPE_TEXT) {
                chat_input_message.setText("")
            }
        }
    }

    private fun initInfoToolbar() {
        if (mReceivingUser.fullname.isEmpty()){
            mToolbarInfo.contact_fullname_chats.text = contact.fullname
        } else mToolbarInfo.contact_fullname_chats.text = mReceivingUser.fullname

        mToolbarInfo.toolbar_image_chats.downloadAndSetImage(mReceivingUser.photoURL)
        mToolbarInfo.contact_state_chats.text = mReceivingUser.state
    }

    override fun onPause() {
        super.onPause()
        mToolbarInfo.visibility = View.GONE
mRefUser.removeEventListener(mListenerInfoToolBar)
        mRefMessages.removeEventListener(mMessagesListener)
    }
}