package com.example.gerfoxmessages.ui.message_recycleView.views_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gerfoxmessages.dataBase.CURRENT_UID
import com.example.gerfoxmessages.ui.message_recycleView.views.MessageView
import com.example.gerfoxmessages.utilits.asTime
import kotlinx.android.synthetic.main.messgae_item_text.view.*

class HolderTextMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
    //TEXT
    private  val blockUserMessage: ConstraintLayout = view.block_user_message
    private  val chatUserMessage: TextView = view.chat_user_message
    private val chatUserMessageTime: TextView = view.chat_user_message_time
    private  val blockReceivedMessage: ConstraintLayout = view.block_reciver_message
    private val chatReceivedMessage: TextView = view.chat_reciver_message
    private val chatReceivedMessageTime: TextView = view.chat_reciver_message_time

    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockUserMessage.visibility = View.VISIBLE
            blockReceivedMessage.visibility = View.GONE
            chatUserMessage.text = view.text
            chatUserMessageTime.text =
                view.timeStamp.asTime()
        } else {
            blockUserMessage.visibility = View.GONE
            blockReceivedMessage.visibility = View.VISIBLE
            chatReceivedMessage.text = view.text
            chatReceivedMessageTime.text =
                view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {

    }

    override fun onDetattach() {

    }
}