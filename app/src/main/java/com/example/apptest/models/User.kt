package com.example.apptest.models

data class User(
    val id: String = "",
    var phone: String = "",
    var username: String = "",
    var fullname: String = "",
    var bio: String = "",
    var status: String = "",
    var photoUrl: String = "empty"
)
