package com.example.gerfoxmessages.utilits

import com.example.gerfoxmessages.dataBase.*

enum class AppStates(val state:String) {
    ONLINE("В сети"),
    OFFLINE("Не в сети"),
    TYPING("Печатает");

    companion object {
        fun updateState(appStates: AppStates) {
            if (AUTH.currentUser != null) {
                REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATE)
                    .setValue(appStates.state)
                    .addOnFailureListener { showToast(it.message.toString()) }
            }

        }
    }
}