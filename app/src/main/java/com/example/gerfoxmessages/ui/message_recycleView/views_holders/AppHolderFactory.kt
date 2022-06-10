package com.example.gerfoxmessages.ui.message_recycleView.views_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gerfoxmessages.ui.message_recycleView.views.MessageView
import gerfoxmessages.R

class AppHolderFactory {

    companion object {
        fun getHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                MessageView.MESSAGE_IMAGE -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.messgae_item_images, parent, false)
                    HolderImageMessage(view)
                }
                MessageView.MESSAGE_VOICE -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.messgae_item_voice, parent, false)
                    HolderVoiceMessage(view)
                }
                MessageView.MESSAGE_FILE -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.messgae_item_file, parent, false)
                    HolderFileMessage(view)
                }
                else -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.messgae_item_text, parent, false)
                    HolderTextMessage(view)
                }
            }
        }
    }
}