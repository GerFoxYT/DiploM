package com.example.apptest.ui.screens.groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptest.R
import com.example.apptest.models.CommonModel
import com.example.apptest.ui.screens.single_chat.SingleChat
import com.example.apptest.utilits.downloadAndSetImage
import com.example.apptest.utilits.replaceFragment
import com.example.apptest.utilits.showToast
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.add_contacts_item.view.*

class addContactsApadter : RecyclerView.Adapter<addContactsApadter.addContactsHolder>() {

    private var listItems = mutableListOf<CommonModel>()

    class addContactsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.add_contacts_fullname
        val itemPhoto: CircleImageView = view.add_contacts_photo
        val itemState: TextView = view.add_contacts_state
        val itemChoice: CircleImageView = view.add_contacts_item_choice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): addContactsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.add_contacts_item, parent, false)
        val holder = addContactsHolder(view)
        holder.itemView.setOnClickListener {
            if (listItems[holder.adapterPosition].choice) {
                holder.itemChoice.visibility = View.INVISIBLE
                listItems[holder.adapterPosition].choice = false
                AddContactsMain.listContacts.remove(listItems[holder.adapterPosition])
            } else {
                holder.itemChoice.visibility = View.VISIBLE
                listItems[holder.adapterPosition].choice = true
                AddContactsMain.listContacts.add(listItems[holder.adapterPosition])
            }
        }

        return holder
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: addContactsHolder, position: Int) {
        holder.itemName.text = listItems[position].fullname
        holder.itemState.text = listItems[position].state
        holder.itemPhoto.downloadAndSetImage(listItems[position].photoURL)

    }

    fun updateListItems(item: CommonModel) {
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }
}