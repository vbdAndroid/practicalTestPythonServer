package com.example.practicaltestpythonserver.mvvm.questionSurvey

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaltestpythonserver.databinding.FragmentQuestionAnswerBinding
import com.example.practicaltestpythonserver.mvvm.activity.userActivityVM
import com.example.practicaltestpythonserver.databinding.MovieScreensMainBinding
import com.example.practicaltestpythonserver.mvvm.dataModel.*
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionAnswerFragment : Fragment() {


    val viewModel: QuestionAnswerVM by viewModels()
    lateinit var binding: FragmentQuestionAnswerBinding
    private lateinit var categories: List<Category>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        viewModel.getQuestion()

    }

    private fun bindObservers() {
        viewModel.questionDataResponse.observe(viewLifecycleOwner) {
            categories = it.inspection.survey.categories
            setupCategorySelector()
            setupSubmitButton()

        }

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
    private fun setupCategorySelector() {
        val categoryNames = categories.map { it.name }
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, categoryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = adapter

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = categories[position]
                    setupQuestionRecyclerView(selectedCategory.questions)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }
    }

    private fun setupQuestionRecyclerView(questions: List<Question>) {
        val adapter = QuestionAdapter(questions)
        binding.questionRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.questionRecyclerView.adapter = adapter
    }

    private fun setupSubmitButton() {
        binding.submitButton.setOnClickListener {
            val response = InspectionResponse(
                Inspection(
                    area = Area(id = 1, name = "Emergency ICU"),
                    id = 1,
                    inspectionType = InspectionType(access = "write", id = 1, name = "Clinical"),
                    survey = Survey(categories = categories, id = 1)
                )
            )

            Toast.makeText(requireContext(), "Answers submitted", Toast.LENGTH_SHORT).show()
            viewModel.submit(response)
        }
    }

    }
