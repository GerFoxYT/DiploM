package com.example.gerfoxmessages.ui.message_recycleView.views_holders

import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.gerfoxmessages.dataBase.CURRENT_UID
import com.example.gerfoxmessages.dataBase.getFileFromStorage
import com.example.gerfoxmessages.ui.message_recycleView.views.MessageView
import com.example.gerfoxmessages.utilits.WRITE_FILES
import com.example.gerfoxmessages.utilits.asTime
import com.example.gerfoxmessages.utilits.checkPermission
import com.example.gerfoxmessages.utilits.showToast
import kotlinx.android.synthetic.main.messgae_item_file.view.*
import java.io.File

class HolderFileMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {


    // File
    private val blockReceivedFileMessage: ConstraintLayout = view.block_reciver_file
    private val blockUserFileMessage: ConstraintLayout = view.block_user_file
    private val chatReceivedFileMessageTime: TextView = view.chat_reciver_file_time
    private val chatUserMessageFileTime: TextView = view.chat_user_file_time

    private val chatUserFilename: TextView = view.chat_file_name_user
    private val chatUserBtnDwn: ImageView = view.chat_user_btn_dwn
    private val chatUserProgressBar: ProgressBar = view.chat_user_progress_bar

    private val chatReceivedFilename: TextView = view.chat_file_name_reciver
    private val chatReceivedBtnDwn: ImageView = view.chat_reciver_btn_dwn
    private val chatReceivedProgressBar: ProgressBar = view.chat_reciver_progress_bar


    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockReceivedFileMessage.visibility = View.GONE
            blockUserFileMessage.visibility = View.VISIBLE
            chatUserMessageFileTime.text = view.timeStamp.asTime()
            chatUserFilename.text = view.text
        } else {
            blockReceivedFileMessage.visibility = View.VISIBLE
            blockUserFileMessage.visibility = View.GONE
            chatReceivedFileMessageTime.text = view.timeStamp.asTime()
            chatReceivedFilename.text = view.text
        }
    }

    override fun onAttach(view: MessageView) {
        if (view.from == CURRENT_UID)
            chatUserBtnDwn.setOnClickListener { clickToBtnFile(view) }
        else chatReceivedBtnDwn.setOnClickListener { clickToBtnFile(view) }
    }


    private fun clickToBtnFile(view: MessageView) {
        if (view.from == CURRENT_UID) {
            chatUserBtnDwn.visibility = View.INVISIBLE
            chatUserProgressBar.visibility = View.VISIBLE
        } else {
            chatReceivedBtnDwn.visibility = View.INVISIBLE
            chatReceivedProgressBar.visibility = View.VISIBLE
        }

        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            view.text
        )
        try {
            if (checkPermission(WRITE_FILES)) {
                file.createNewFile()
                getFileFromStorage(file, view.fileURL) {
                    if (view.from == CURRENT_UID) {
                        chatUserBtnDwn.visibility = View.VISIBLE
                        chatUserProgressBar.visibility = View.INVISIBLE
                    } else {
                        chatReceivedBtnDwn.visibility = View.VISIBLE
                        chatReceivedProgressBar.visibility = View.INVISIBLE
                    }
                }
            }
        } catch (e: Exception) {
            showToast(e.message.toString())

        }
    }

    override fun onDetattach() {
        chatUserBtnDwn.setOnClickListener(null)
        chatReceivedBtnDwn.setOnClickListener(null)
    }

}