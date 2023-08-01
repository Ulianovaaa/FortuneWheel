package com.izzzya.fortunewheel

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import kotlin.random.Random


class QuestionFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val header = view.findViewById<TextView>(R.id.questionTV)
        val ans1 = view.findViewById<Button>(R.id.answerBtn1)
        val ans2 = view.findViewById<Button>(R.id.answerBtn2)
        val ans3 = view.findViewById<Button>(R.id.answerBtn3)
        fun rand(start: Int, end: Int): Int {
            require(start <= end) { "Illegal Argument" }
            return (start..end).random()
        }
        val question: Question = QuestionsSource.questionsList[rand(0, 4)]

        header.text = question.question
        ans1.text = question.listAnswers[0]
        ans2.text = question.listAnswers[1]
        ans3.text = question.listAnswers[2]

        ans1.setOnClickListener(){
            answerCheck(ans1, question)
        }
        ans2.setOnClickListener(){
            answerCheck(ans2, question)
        }
        ans3.setOnClickListener(){
            answerCheck(ans3, question)
        }
    }

    private fun answerCheck(btn: Button, q: Question) {
        val sound = MediaPlayer.create(requireContext(), R.raw.sound)
        sound.setVolume(SharedPrefs.getSVol(), SharedPrefs.getSVol())
        //проверка ответа
        if (btn.text == q.listAnswers[q.answerIndex])
        {
            Toast.makeText(context, "Верно", Toast.LENGTH_SHORT).show()
            sound.start()
        } else Toast.makeText(context, "Неверно", Toast.LENGTH_SHORT).show()

    }
}