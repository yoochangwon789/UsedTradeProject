package com.yoochangwonspro.usedtradeproject.mypage

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.yoochangwonspro.usedtradeproject.R
import com.yoochangwonspro.usedtradeproject.databinding.FragmentMypageBinding

class MyPageFragment : Fragment(R.layout.fragment_mypage) {

    private var binding: FragmentMypageBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentMyPageBinding = FragmentMypageBinding.bind(view)
        binding = fragmentMyPageBinding

        fragmentMyPageBinding.signUpButton.setOnClickListener {
            binding?.let { binding ->
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

            }
        }

        fragmentMyPageBinding.signInOutButton.setOnClickListener {
            binding?.let { binding ->
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

            }
        }

        fragmentMyPageBinding.emailEditText.addTextChangedListener {
            binding?.let { binding ->
                val enable = binding.emailEditText.text.isNotEmpty()
                        && binding.passwordEditText.text.isNotEmpty()

                binding.signInOutButton.isEnabled = enable
                binding.signUpButton.isEnabled = enable
            }
        }

        fragmentMyPageBinding.passwordEditText.addTextChangedListener {
            binding?.let { binding ->
                val enable = binding.emailEditText.text.isNotEmpty()
                        && binding.passwordEditText.text.isNotEmpty()

                binding.signInOutButton.isEnabled = enable
                binding.signUpButton.isEnabled = enable
            }
        }
    }
}