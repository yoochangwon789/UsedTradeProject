package com.yoochangwonspro.usedtradeproject.mypage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yoochangwonspro.usedtradeproject.R
import com.yoochangwonspro.usedtradeproject.databinding.FragmentMypageBinding

class MyPageFragment : Fragment(R.layout.fragment_mypage) {

    private var binding: FragmentMypageBinding? = null
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentMyPageBinding = FragmentMypageBinding.bind(view)
        binding = fragmentMyPageBinding

        fragmentMyPageBinding.signUpButton.setOnClickListener {
            binding?.let { binding ->
                val email = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (auth.currentUser == null) {
                    // 로그인
                    activity?.let { activity ->
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(activity) {
                                // requireActivity 은 nonNull 형태로 null 인경우 Exception 을 발생 시킬 수 있기 때문에 그대로 쓰면 위험할 수 있다
                                // 그렇기 때문에 getActivity 를 가져와 null 처리를 하고 사용하는 것이 더 안전하고 좋은 코드라고 할 수 있다.
                                if (it.isSuccessful) {
                                    successSignIn()
                                } else {
                                    Toast.makeText(context, "로그인을 실패했습니다.", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                } else {
                    // 로그아웃
                    auth.signOut()

                    binding.apply {
                        emailEditText.text.clear()
                        emailEditText.isEnabled = true
                        passwordEditText.text.clear()
                        passwordEditText.isEnabled = true

                        signInOutButton.text = "로그인"
                        signInOutButton.isEnabled = false
                        signUpButton.isEnabled = false
                    }
                }
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

    private fun successSignIn() {
        if (auth.currentUser == null) {
            Toast.makeText(context, "로그인을 실패했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
            return
        }
    }
}