package com.example.apptest.ui.message_recycleView.views_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.dataBase.CURRENT_UID
import com.example.apptest.ui.message_recycleView.views.MessageView
import com.example.apptest.utilits.asTime
import com.example.apptest.utilits.downloadAndSetImage
import kotlinx.android.synthetic.main.messgae_item_images.view.*

class HolderImageMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {
    // IMAGE
    private  val blockReceivedImage: ConstraintLayout = view.block_reciver_image
    private val blockUserImage: ConstraintLayout = view.block_user_image
    private val chatUserImage: ImageView = view.chat_user_image
    private val chatReceivedImage: ImageView = view.chat_reciver_image
    private val chatReceivedImageMessageTime: TextView = view.chat_reciver_image_time
    private val chatUserMessageImageTime: TextView = view.chat_user_image_time

    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockReceivedImage.visibility = View.GONE
            blockUserImage.visibility = View.VISIBLE
            chatUserImage.downloadAndSetImage(view.fileURL)
            chatUserMessageImageTime.text =
                view.timeStamp.asTime()
        } else {
            blockReceivedImage.visibility = View.VISIBLE
            blockUserImage.visibility = View.GONE
            chatReceivedImage.downloadAndSetImage(view.fileURL)
            chatReceivedImageMessageTime.text =
                view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {

    }

    override fun onDetattach() {

    }
}