package com.yoochangwonspro.usedtradeproject.chatlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yoochangwonspro.usedtradeproject.R
import com.yoochangwonspro.usedtradeproject.databinding.FragmentChatlistBinding

class ChatListFragment : Fragment(R.layout.fragment_chatlist) {

    private var binding: FragmentChatlistBinding? = null
    private lateinit var chatListAdapter: ChatListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentChatListBinding = FragmentChatlistBinding.bind(view)
        binding = fragmentChatListBinding

        chatListAdapter = ChatListAdapter(
            onItemClicked = {

            }
        )

        fragmentChatListBinding.chatListRecyclerView.apply {
            adapter = chatListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}