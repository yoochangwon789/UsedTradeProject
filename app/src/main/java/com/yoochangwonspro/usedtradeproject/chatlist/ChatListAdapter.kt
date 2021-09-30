package com.yoochangwonspro.usedtradeproject.chatlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwonspro.usedtradeproject.databinding.ItemChatListBinding

class ChatListAdapter(val onItemClicked: (ChatListItem) -> Unit) : ListAdapter<ChatListItem, ChatListAdapter.ChatListViewHolder>(diffUtil) {

    inner class ChatListViewHolder(private val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chatListItem: ChatListItem) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        return ChatListViewHolder(
            ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatListItem>() {
            override fun areItemsTheSame(oldItem: ChatListItem, newItem: ChatListItem): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: ChatListItem, newItem: ChatListItem): Boolean {
                TODO("Not yet implemented")
            }

        }
    }
}