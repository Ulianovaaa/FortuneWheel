package com.izzzya.fortunewheel

import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlin.random.Random


class QuestionFragment : Fragment() {
private var answered = false

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
        val imgView: ImageView = view.findViewById(R.id.gifIV)
        imgView.visibility = View.GONE
        requireActivity().findViewById<ImageView>(R.id.backBtn).visibility = View.GONE
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
            if (!answered)
            answerCheck(ans1, question)
        }
        ans2.setOnClickListener(){
            if (!answered)
            answerCheck(ans2, question)
        }
        ans3.setOnClickListener(){
            if (!answered)
            answerCheck(ans3, question)
        }
    }

    private fun answerCheck(btn: Button, q: Question) {
        Log.i("ANS CHECK CUR COINS:", SharedPrefs.getCurCoins().toString())
        val sound = MediaPlayer.create(requireContext(), R.raw.sound)
        sound.setVolume(SharedPrefs.getSVol(), SharedPrefs.getSVol())
        //проверка ответа
        if (btn.text == q.listAnswers[q.answerIndex])
        {
            btn.setBackgroundColor(getResources().getColor(R.color.green))
            //Toast.makeText(context, "Верно", Toast.LENGTH_SHORT).show()
            sound.start()
            Log.i("ANS RIGHT CUR COINS:", SharedPrefs.getCurCoins().toString())
            val earned = SharedPrefs.getCurCoins()
            SharedPrefs.setCoins(earned)
            Log.i("ANS RIGHT COINS:", SharedPrefs.getCoins().toString())
            requireActivity().findViewById<TextView>(R.id.moneyTV).text = SharedPrefs.getCoins().toString()
            val imgView: ImageView = view!!.findViewById(R.id.gifIV)
            imgView.visibility = View.VISIBLE
            Thread{
                val source = ImageDecoder.createSource(resources, R.drawable.giphy)
                val drawable: Drawable = ImageDecoder.decodeDrawable(source)
                imgView.post{
                    imgView.setImageDrawable(drawable)
                    (drawable as? AnimatedImageDrawable)?.start()
                }
            }.start()
        } else{
            Log.i("ANS WRONG CUR COINS:", SharedPrefs.getCurCoins().toString())
            Log.i("ANS WRONG  COINS:", SharedPrefs.getCoins().toString())
            btn.setBackgroundColor(getResources().getColor(R.color.red))
            //Toast.makeText(context, "Неверно", Toast.LENGTH_SHORT).show()
            requireActivity().findViewById<TextView>(R.id.moneyTV).text = SharedPrefs.getCoins().toString()

        }
        SharedPrefs.setCurCoins(0)
        answered = true
        requireActivity().findViewById<ImageView>(R.id.backBtn).visibility = View.VISIBLE
        //findNavController().navigate(R.id.action_global_wheelFragment)

    }
}