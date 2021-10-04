package com.yoochangwonspro.usedtradeproject.chatlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yoochangwonspro.usedtradeproject.DBKey.Companion.CHILD_CHAT
import com.yoochangwonspro.usedtradeproject.DBKey.Companion.DB_USERS
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

        val chatDB = Firebase.database.reference
            .child(DB_USERS).child(auth.currentUser?.uid.orEmpty()).child(CHILD_CHAT)

        chatDB.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                /*
                * ChatListItem 객체 타입의 데이터가 여러개일 경우 snapshot 은 List 를 가진다
                * 그러므로 snapshot 의 하위 데이터 children 키워드를 사용해(chatDB 에 child 는 CHILD_CHAT 까지의 데이터 그하위에 있는 데이터를 가져오기 위함) forEach 문을 돌려서
                * getValue 함수를 사용해 자바의 객체 타입을 반환받고 (ChatListItem) 타입을 반환
                * 반환 받은 model 을 adapter 에게 전달하고 RecyclerView 에 데이터들을 출력한다
               * */

                snapshot.children.forEach {
                    val model = it.getValue(ChatListItem::class.java)
                    model ?: return

                    chatRoomList.add(model)
                }
                chatListAdapter.submitList(chatRoomList)
                chatListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onResume() {
        super.onResume()
        chatListAdapter.notifyDataSetChanged()
    }
}