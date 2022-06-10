package com.example.gerfoxmessages.ui.message_recycleView.views_holders

import com.example.gerfoxmessages.ui.message_recycleView.views.MessageView

interface MessageHolder {
    fun drawMessage(view: MessageView)
    fun onAttach(view: MessageView)
    fun onDetattach()
}