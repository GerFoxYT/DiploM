package com.example.apptest.utilits

import com.example.apptest.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var USER: User
lateinit var REF_STORAGE_ROOT:StorageReference

const val NODE_USERS = "users"
const val NODE_USERNAMES = "usernames"
const val FOLDER_PROFILE_IMAGE = "profile_image"

const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"
const val CHILD_FULLNAME = "fullname"
const val CHILD_BIO = "bio"
const val CHILD_PHOTO_URL = "photoURL"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT =
        FirebaseDatabase.getInstance("https://gerfox-message-default-rtdb.europe-west1.firebasedatabase.app/").reference
    USER = User()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    REF_STORAGE_ROOT = FirebaseStorage.getInstance("gs://gerfox-message.appspot.com").reference
}



