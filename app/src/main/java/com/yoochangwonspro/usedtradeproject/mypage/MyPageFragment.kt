package com.yoochangwonspro.usedtradeproject.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yoochangwonspro.usedtradeproject.R
import com.yoochangwonspro.usedtradeproject.databinding.FragmentMypageBinding

class MyPageFragment : Fragment(R.layout.fragment_mypage) {

    private var binding: FragmentMypageBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentMyPageBinding = FragmentMypageBinding.bind(view)
        binding = fragmentMyPageBinding
    }
}