package com.example.apptest.ui.screens.groups

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.dataBase.*
import com.example.apptest.models.CommonModel
import com.example.apptest.ui.screens.base.Base
import com.example.apptest.ui.screens.main_list.MainList
import com.example.apptest.utilits.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.create_group.*
import kotlinx.android.synthetic.main.fragment_settings.*

class CreateGroup(private var listContacts: List<CommonModel>) : Base(R.layout.create_group) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: addContactsApadter
    private  var mUri = Uri.EMPTY

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Создать группу"
        hideKeyboard()
        initRecyclerView()
        create_group_image.setOnClickListener { addPhoto() }
        create_group_btn_comp.setOnClickListener {
            val nameGroup = create_group_input.text.toString()
            if (nameGroup.isEmpty()){
               showToast("Введите название группы")
            } else {
                createGroupToDatabase(nameGroup, mUri, listContacts){

                    replaceFragment(MainList())
                }
            }
        }
        create_group_input.requestFocus()
        create_group_counts.text = getPlurals(listContacts.size)
    }



    private fun addPhoto() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(250, 250)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY,this)
    }

    private fun initRecyclerView() {
        mRecyclerView = create_group_recycler_view
        mAdapter = addContactsApadter()
        mRecyclerView.adapter = mAdapter
        listContacts.forEach { mAdapter.updateListItems(it) }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null
        ) {
            mUri = CropImage.getActivityResult(data).uri
            create_group_image.setImageURI(mUri)

                    }
                }
            }
