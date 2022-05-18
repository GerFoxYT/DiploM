package com.example.apptest.ui.message_recycleView.views

data class ViewImagesMessage(
    override val id: String,
    override val from: String,
    override val timeStamp: String,
    override val fileURL: String,
    override val text: String = ""

) : MessageView {
    override fun getTypeView(): Int {
        return MessageView.MESSAGE_IMAGE
    }

    override fun equals(other: Any?): Boolean {
        return (other as MessageView).id == id
    }
}