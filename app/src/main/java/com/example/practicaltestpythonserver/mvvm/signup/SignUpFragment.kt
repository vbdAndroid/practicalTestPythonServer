package com.example.practicaltestpythonserver.mvvm.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicaltestpythonserver.mvvm.dataInterface.MovieItemClickListener
import com.example.practicaltestpythonserver.mvvm.activity.userActivityVM
import com.example.practicaltestpythonserver.databinding.MovieScreensMainBinding
import com.example.practicaltestpythonserver.databinding.SignUpLayouyBinding
import com.example.practicaltestpythonserver.mvvm.LoginFragmentDirections
import com.example.practicaltestpythonserver.mvvm.activity.ScreenListAdapter
import com.example.practicaltestpythonserver.mvvm.dataModel.screenData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    val viewModel: userActivityVM by viewModels()
    lateinit var binding: SignUpLayouyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpLayouyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        clickListener()
    }
    private fun clickListener(){
        binding.btnRegister.setOnClickListener {
            viewModel.register("john2@email.com", "dogsname201")

        }
    }

   private fun bindObservers() {
        viewModel.statusCode.observe(viewLifecycleOwner) { statusCode ->

            when (statusCode) {
                200 -> {
                    findNavController().navigateUp()
                }
                400 -> println("Bad request: ")
                401 -> println("Unauthorized: ")
                else -> println("Unexpected status code:")
            }
        }
    }


}
