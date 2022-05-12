package com.example.apptest.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.models.CommonModel
import com.example.apptest.utilits.*
import kotlinx.android.synthetic.main.messgae_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = mutableListOf<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {

        //TEXT
        val blockUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time
        val blockReceivedMessage: ConstraintLayout = view.block_reciver_message
        val chatReceivedMessage: TextView = view.chat_reciver_message
        val chatReceivedMessageTime: TextView = view.chat_reciver_message_time

        // IMAGE
        val blockReceivedImage: ConstraintLayout = view.block_reciver_image
        val blockUserImage: ConstraintLayout = view.block_user_image
        val chatUserImage: ImageView = view.chat_user_image
        val chatReceivedImage: ImageView = view.chat_reciver_image
        val chatReceivedImageMessageTime: TextView = view.chat_reciver_image_time
        val chatUserMessageImageTime: TextView = view.chat_user_image_time

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.messgae_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        when (mListMessagesCache[position].type) {
            TYPE_MESSAGE_TEXT -> drawMessageText(holder, position)
            TYPE_MESSAGE_IMAGE -> drawMessageImage(holder, position)
        }
    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.blockUserMessage.visibility = View.GONE
        holder.blockReceivedMessage.visibility = View.GONE
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blockReceivedImage.visibility = View.GONE
            holder.blockUserImage.visibility = View.VISIBLE
            holder.chatUserImage.downloadAndSetImage(mListMessagesCache[position].imageURL)
            holder.chatUserMessageImageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockReceivedImage.visibility = View.VISIBLE
            holder.blockUserImage.visibility = View.GONE
            holder.chatReceivedImage.downloadAndSetImage(mListMessagesCache[position].imageURL)
            holder.chatReceivedImageMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.blockReceivedImage.visibility = View.GONE
        holder.blockUserImage.visibility = View.GONE
        if (mListMessagesCache[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessagesCache[position].text
            holder.chatReceivedMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    fun addItemToBotton(item: CommonModel, onSuccess: () -> Unit) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            notifyItemInserted(mListMessagesCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(item: CommonModel, onSuccess: () -> Unit) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }
}

