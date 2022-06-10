package com.example.gerfoxmessages.ui.screens.main_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerfoxmessages.models.CommonModel
import com.example.gerfoxmessages.ui.screens.groups.GroupChat
import com.example.gerfoxmessages.ui.screens.single_chat.SingleChat
import com.example.gerfoxmessages.utilits.TYPE_CHAT
import com.example.gerfoxmessages.utilits.TYPE_GROUP
import com.example.gerfoxmessages.utilits.downloadAndSetImage
import com.example.gerfoxmessages.utilits.replaceFragment
import de.hdodenhof.circleimageview.CircleImageView
import gerfoxmessages.R
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.MainListHolder>() {

    private var listItems = mutableListOf<CommonModel>()

    class MainListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.main_list_fullname
        val itemLastMessage: TextView = view.main_list_last_message
        val itemPhoto: CircleImageView = view.main_list_photo
        val itemState: TextView = view.main_list_state
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)
        val holder = MainListHolder(view)
        holder.itemView.setOnClickListener {
            when (listItems[holder.adapterPosition].type) {
                TYPE_CHAT -> replaceFragment(SingleChat(listItems[holder.adapterPosition]))
                TYPE_GROUP -> replaceFragment(GroupChat(listItems[holder.adapterPosition]))
            }

        }

        return holder
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.itemName.text = listItems[position].fullname
        holder.itemLastMessage.text = listItems[position].lastMessage
        holder.itemState.text = listItems[position].state
        holder.itemPhoto.downloadAndSetImage(listItems[position].photoURL)
    }

    fun updateListItems(item: CommonModel) {
        listItems.add(item)
        notifyItemInserted(listItems.size)
    }
}