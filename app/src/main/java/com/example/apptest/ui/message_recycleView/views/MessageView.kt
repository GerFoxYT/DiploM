package com.example.apptest.ui.message_recycleView.views

interface MessageView {
    val id: String
    val from: String
    val timeStamp: String
    val fileURL: String
    val text: String

    companion object {
        val MESSAGE_IMAGE: Int
        get() = 0
        val MESSAGE_TEXT: Int
        get() = 1
        val MESSAGE_VOICE: Int
            get() = 2
    }
fun  getTypeView():Int

}