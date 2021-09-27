package com.yoochangwonspro.usedtradeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yoochangwonspro.usedtradeproject.chatlist.ChatListFragment
import com.yoochangwonspro.usedtradeproject.home.HomeFragment
import com.yoochangwonspro.usedtradeproject.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val chatListFragment = ChatListFragment()
        val myPageFragment = MyPageFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {}
                R.id.chatList -> {}
                R.id.myPage -> {}
            }
            true
        }
    }
}