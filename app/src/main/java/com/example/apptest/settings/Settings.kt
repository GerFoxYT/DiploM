package com.example.apptest.settings

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.apptest.R
import com.example.apptest.dataBase.*
import com.example.apptest.ui.screens.base.Base
import com.example.apptest.ui.screens.register.ChangeBio
import com.example.apptest.ui.screens.register.ChangeName
import com.example.apptest.ui.screens.register.ChangeUsername
import com.example.apptest.utilits.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_settings.*

class Settings : Base(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        s_bio.text = USER.bio
        s_full_name.text = USER.fullname
        s_phoneNum.text = USER.phone
        state.text = USER.state
        s_input_username.text = USER.username
        s_btn_change_name.setOnClickListener { replaceFragment(ChangeUsername()) }
        s_btn_change_status.setOnClickListener { replaceFragment(ChangeBio()) }
        s_profile_image.setOnClickListener { changePhotoUser() }
        s_profile_image.downloadAndSetImage(USER.photoURL)
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(250, 250)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY,this)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.s_btn_exit -> {
                AppStates.updateState(AppStates.OFFLINE)
                AUTH.signOut()
                restartActivity()
            }
            R.id.s_menu_change_name -> replaceFragment(ChangeName())
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_UID)
            putFileToStorage(uri,path){
                getUrlFromStorage(path){
                    putUrlToDatabase(it){
                        s_profile_image.downloadAndSetImage(it)
                        showToast(getString(R.string.app_data_update))
                        USER.photoURL = it
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                    }
                }
            }
        }
    }

}
