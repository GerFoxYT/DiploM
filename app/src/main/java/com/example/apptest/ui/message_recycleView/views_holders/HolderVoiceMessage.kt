package com.example.apptest.ui.message_recycleView.views_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.dataBase.CURRENT_UID
import com.example.apptest.ui.message_recycleView.views.MessageView
import com.example.apptest.utilits.AppVoicePlayer
import com.example.apptest.utilits.asTime
import kotlinx.android.synthetic.main.messgae_item_voice.view.*

class HolderVoiceMessage(view: View) : RecyclerView.ViewHolder(view), MessageHolder {

    private val mAppVoicePlayer = AppVoicePlayer()

    // Voice
    private val blockReceivedVoiceMessage: ConstraintLayout = view.block_reciver_voice
    private val blockUserVoiceMessage: ConstraintLayout = view.block_user_voice
    private val chatReceivedVoiceMessageTime: TextView = view.chat_reciver_voice_time
    private  val chatUserMessageVoiceTime: TextView = view.chat_user_voice_time


    private  val chatReceivedBtnPlay: ImageView = view.chat_reciver_btn_play
    private val chatReceivedBtnStop: ImageView = view.chat_reciver_btn_stop

    private  val chatUserBtnPlay: ImageView = view.chat_user_btn_play
    private  val chatUserBtnStop: ImageView = view.chat_user_btn_stop


    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blockReceivedVoiceMessage.visibility = View.GONE
            blockUserVoiceMessage.visibility = View.VISIBLE
            chatUserMessageVoiceTime.text =
                view.timeStamp.asTime()
        } else {
            blockReceivedVoiceMessage.visibility = View.VISIBLE
            blockUserVoiceMessage.visibility = View.GONE
            chatReceivedVoiceMessageTime.text =
                view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {
        mAppVoicePlayer.init()
        if (view.from == CURRENT_UID) {
            chatUserBtnPlay.setOnClickListener {
                chatUserBtnPlay.visibility = View.GONE
                chatUserBtnStop.visibility = View.VISIBLE
                chatUserBtnStop.setOnClickListener {
                    stop {
                        chatUserBtnStop.setOnClickListener(null)
                        chatUserBtnPlay.visibility = View.VISIBLE
                        chatUserBtnStop.visibility = View.GONE
                    }
                }
                play(view) {
                    chatUserBtnPlay.visibility = View.VISIBLE
                    chatUserBtnStop.visibility = View.GONE
                }
            }
        } else {
            chatReceivedBtnPlay.setOnClickListener {
                chatReceivedBtnPlay.visibility = View.GONE
                chatReceivedBtnStop.visibility = View.VISIBLE
                chatReceivedBtnStop.setOnClickListener {
                    stop {
                        chatReceivedBtnStop.setOnClickListener(null)
                        chatReceivedBtnPlay.visibility = View.VISIBLE
                        chatReceivedBtnStop.visibility = View.GONE
                    }
                }
                play(view) {
                    chatReceivedBtnPlay.visibility = View.VISIBLE
                    chatReceivedBtnStop.visibility = View.GONE
                }

            }
        }
    }

    private fun play(view: MessageView, function: () -> Unit) {
        mAppVoicePlayer.play(view.id, view.fileURL) {
            function()
        }
    }

    override fun onDetattach() {
        chatReceivedBtnPlay.setOnClickListener { null }
        chatUserBtnPlay.setOnClickListener { null }
        mAppVoicePlayer.release()
    }

    fun stop(function: () -> Unit) {
        mAppVoicePlayer.stop {
            function()
        }
    }
}