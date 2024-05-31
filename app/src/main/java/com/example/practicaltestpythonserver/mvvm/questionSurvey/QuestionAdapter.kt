package com.example.practicaltestpythonserver.mvvm.questionSurvey

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltestpythonserver.R
import com.example.practicaltestpythonserver.mvvm.dataModel.Question

class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.question_text)
        private val answersRadioGroup: RadioGroup = itemView.findViewById(R.id.answers_radio_group)

        fun bind(question: Question) {
            questionText.text = question.name
            answersRadioGroup.removeAllViews()

            question.answerChoices.forEach { answer ->
                val radioButton = RadioButton(itemView.context)
                radioButton.text = answer.name
                radioButton.id = answer.id
                answersRadioGroup.addView(radioButton)
            }

            // Preselect the radio button if it has been already selected
            if (question.selectedAnswerChoiceId != null) {
                answersRadioGroup.check(question.selectedAnswerChoiceId!!)
            }

            answersRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                question.selectedAnswerChoiceId = checkedId
            }
        }
    }
}

