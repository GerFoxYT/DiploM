package com.example.apptest.models

data class User(
    val id:String = "",
    var username:String = "",
    var bio:String = "",
    var phone:String = "",
    var fullname:String = "",
    var state:String = "",
    var photoURL:String = "empty"
)