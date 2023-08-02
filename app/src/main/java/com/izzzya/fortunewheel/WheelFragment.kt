package com.izzzya.fortunewheel

import android.animation.Animator.AnimatorListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import java.util.Random


class WheelFragment : Fragment() {
    private val sectors = arrayOf(75, 45, 25, 750, 450, 200, 125, 100, )
    private var sectorDegrees = IntArray(sectors.size)
    private var rndSectorIndex = 0
    var spinning = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wheel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<ImageView>(R.id.backBtn).visibility = View.GONE
        val spinBtn = view.findViewById<Button>(R.id.spinBtn)
        val earned = 0
        val wheel = view.findViewById<ImageView>(R.id.wheelIV)
        generateSectorDegrees()

        fun spin() {
            rndSectorIndex = Random().nextInt(sectors.size)
            val rndDegree = generateRandomDegree()
            val anim = RotateAnimation(0f, rndDegree.toFloat(), RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF,
                0.5f)
            anim.duration = 3600
            anim.fillAfter = true
            anim.interpolator = DecelerateInterpolator()
            anim.setAnimationListener(object: Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    val earnedCoins = sectors[sectors.size-(rndSectorIndex+1)]
                    SharedPrefs.setCoins(earnedCoins)
                    //Toast.makeText(requireContext(), earnedCoins.toString(), Toast.LENGTH_SHORT).show()
                    spinning = false
                    findNavController().navigate(R.id.action_global_questionFragment)

                }

                override fun onAnimationRepeat(p0: Animation?) {
                }

            })

            wheel.startAnimation(anim)
        }

        spinBtn.setOnClickListener(){
            if (!spinning){
                spin()
                spinning = true
            }

        }
    }



    private fun generateRandomDegree():Int {
        return (360*sectors.size) + sectorDegrees[rndSectorIndex]
    }

    private fun generateSectorDegrees() {
        val sectorDegree = 360/sectors.size
        for (i in 0..sectors.size-1){
            sectorDegrees[i] = (i+1)*sectorDegree
        }
    }

}