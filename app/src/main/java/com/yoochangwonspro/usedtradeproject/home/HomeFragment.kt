package com.yoochangwonspro.usedtradeproject.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
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

    private val articleList = mutableListOf<ArticleModel>()
    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val articleModel = snapshot.getValue(ArticleModel::class.java)
            articleModel ?: return

            articleList.add(articleModel)
            articleAdapter.submitList(articleList)
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        articleDB = Firebase.database.reference.child(DB_ARTICLES)
        articleAdapter = ArticleAdapter()

        fragmentHomeBinding.articleRecyclerView.layoutManager = LinearLayoutManager(context)
        fragmentHomeBinding.articleRecyclerView.adapter = articleAdapter


        articleDB.addChildEventListener(listener)
    }

    // 뷰가 다시 onResume 상태일때 adapter 의 데이터를 갱신한다.
    override fun onResume() {
        super.onResume()
        articleAdapter.notifyDataSetChanged()
    }

    /*
     * Activity 같은 경우에는 엑티비티를 종료하면 View 가 destroy 가 되면서 이벤트가 다 사라지게 되지만
     * Fragment 라이프 싸이클 같은 경우에는 메뉴 하단 탭을 바꾸고 다시 원래의 탭으로 돌아오게 되면 뷰가
     * 계속 그려질 수 있고(재사용) 이벤트 콜백같은 경우 onViewCreated 호출 될 때 마다 중복된 이벤트가 계속 생길 수 있다.
     * 그래서 onDestroyView 가 호출되었을 때 이벤트를 지워주는 형식으로 구현해야 한다
     * */
    override fun onDestroyView() {
        super.onDestroyView()

        articleDB.removeEventListener(listener)
    }
}