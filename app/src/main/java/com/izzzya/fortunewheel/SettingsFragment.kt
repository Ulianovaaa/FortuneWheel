package com.izzzya.fortunewheel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class SettingsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //values from 1 to 100
        val musicVolBar = view.findViewById<SeekBar>(R.id.seekBar)
        val soundVolBar = view.findViewById<SeekBar>(R.id.seekBar2)
        musicVolBar.progress = (SharedPrefs.getMVol()*100).toInt()
        soundVolBar.progress = (SharedPrefs.getSVol()*100).toInt()


        musicVolBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                val vol: Float = (progress.toFloat()/100)
                SharedPrefs.setMVol(vol)
//                Log.i("SEEKBAR VAL: ", vol.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                Toast.makeText(requireContext(), "Re-open app to see changes", Toast.LENGTH_SHORT).show()

            }
        })

        soundVolBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int,
                fromUser: Boolean
            ) {
                val vol: Float = (progress.toFloat()/100)
                SharedPrefs.setSVol(vol)
//                Log.i("SEEKBAR VAL: ", vol.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
    }

    }