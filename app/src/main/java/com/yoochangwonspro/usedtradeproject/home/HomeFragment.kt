package com.yoochangwonspro.usedtradeproject.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yoochangwonspro.usedtradeproject.DBKey.Companion.DB_ARTICLES
import com.yoochangwonspro.usedtradeproject.R
import com.yoochangwonspro.usedtradeproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var articleAdapter: ArticleAdapter
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private lateinit var articleDB: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        articleDB = Firebase.database.reference.child(DB_ARTICLES)

        articleAdapter = ArticleAdapter()
        fragmentHomeBinding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentHomeBinding.articleRecyclerView.adapter = articleAdapter
    }
}