package com.example.practicaltestpythonserver.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practicaltestpythonserver.mvvm.activity.userActivityVM
import com.example.practicaltestpythonserver.databinding.MovieScreensMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    val viewModel: userActivityVM by viewModels()
    lateinit var binding: MovieScreensMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieScreensMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        clickListener()

    }


    private fun clickListener() {
        binding.btnLogin.setOnClickListener {
            viewModel.loginTest(binding.edtUserName.text.toString(),binding.edtPassword.text.toString())
        }
        binding.txtSignUp.setOnClickListener {
            val action =
                LoginFragmentDirections.actionMainScreenFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
    }

    private fun bindObservers() {
        viewModel.statusCode.observe(viewLifecycleOwner) { statusCode ->
            if (statusCode == 200) {
                val action = LoginFragmentDirections.actionMainScreenFragmentToQuestionFragment()
                findNavController().navigate(action)
            }
        }
    }


}
