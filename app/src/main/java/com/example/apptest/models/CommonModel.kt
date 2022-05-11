package com.example.apptest.models

data class CommonModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var phone: String = "",
    var fullname: String = "",
    var state: String = "",
    var photoURL: String = "empty",

    var text:String = "",
    var type:String = "",
    var from:String = "",
    var timeStamp:Any = ""
)