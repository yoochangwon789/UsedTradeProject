package com.yoochangwonspro.usedtradeproject.chatdetail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yoochangwonspro.usedtradeproject.R

class ChatRoomActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    private val chatList = mutableListOf<ChatItem>()

    private val adapter = ChatItemAdapter()

    private val chatRecyclerView: RecyclerView by lazy {
        findViewById(R.id.chatRecyclerView)
    }

    private val sendButton: Button by lazy {
        findViewById(R.id.sendButton)
    }

    private val messageEditText: EditText by lazy {
        findViewById(R.id.messageEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        chatRecyclerView.adapter = adapter
        chatRecyclerView.layoutManager = LinearLayoutManager(this)

        sendButton.setOnClickListener {
            val chatList = auth.currentUser?.uid?.let { uid->
                ChatItem(
                    senderId = uid,
                    message = messageEditText.text.toString()
                )
            }
        }

    }
}