package com.yoochangwonspro.usedtradeproject.chatlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yoochangwonspro.usedtradeproject.R
import com.yoochangwonspro.usedtradeproject.databinding.FragmentChatlistBinding

class ChatListFragment : Fragment(R.layout.fragment_chatlist) {

    private var binding: FragmentChatlistBinding? = null
    private lateinit var chatListAdapter: ChatListAdapter
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val chatRoomList = mutableListOf<ChatListItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentChatListBinding = FragmentChatlistBinding.bind(view)
        binding = fragmentChatListBinding

        chatListAdapter = ChatListAdapter(
            onItemClicked = {
                // 채팅방으로 이동하는 코드
            }
        )

        chatRoomList.clear()

        fragmentChatListBinding.chatListRecyclerView.apply {
            adapter = chatListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        if (auth.currentUser == null) {
            return
        }
    }

    override fun onResume() {
        super.onResume()
        chatListAdapter.notifyDataSetChanged()
    }
}