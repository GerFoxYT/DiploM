package com.example.apptest.ui.message_recycleView.views

import com.example.apptest.models.CommonModel
import com.example.apptest.utilits.TYPE_MESSAGE_FILE
import com.example.apptest.utilits.TYPE_MESSAGE_IMAGE
import com.example.apptest.utilits.TYPE_MESSAGE_VOICE

class AppViewFactory {
    companion object {
        fun getView(message: CommonModel): MessageView {
            return when (message.type) {
                TYPE_MESSAGE_IMAGE -> ViewImagesMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.FileURL
                )
                TYPE_MESSAGE_VOICE -> ViewVoiceMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.FileURL
                )
                TYPE_MESSAGE_FILE -> ViewFileMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.FileURL,
                    message.text
                )
                else -> ViewTextMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.FileURL,
                    message.text
                )
            }
        }
    }
}