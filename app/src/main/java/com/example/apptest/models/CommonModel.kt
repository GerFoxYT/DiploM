package com.example.apptest.models

data class CommonModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var phone: String = "",
    var fullname: String = "",
    var state: String = "",
    var photoURL: String = "empty",
    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timeStamp: Any = "",
    var FileURL: String = "empty",
    var lastMessage: String = "",
    var choice: Boolean = false


) {
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }
}