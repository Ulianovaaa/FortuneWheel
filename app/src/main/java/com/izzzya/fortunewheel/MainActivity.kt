package com.izzzya.fortunewheel

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPrefs(this)
        val view = findViewById<View>(R.id.nav_host_fragment)

        val infoBtn = findViewById<ImageView>(R.id.infoBtn)
        val settingsBtn = findViewById<ImageView>(R.id.settingsBtn)
        val backBtn = findViewById<ImageView>(R.id.backBtn)
        infoBtn.setOnClickListener(){
            view.findNavController().navigate(R.id.action_global_infoFragment)
        }
        settingsBtn.setOnClickListener(){
            view.findNavController().navigate(R.id.action_global_settingsFragment)
        }
        backBtn.setOnClickListener(){
            view.findNavController().navigate(R.id.action_global_wheelFragment)
        }

        //music
        //сява джекпот минус
        // )))
        player = MediaPlayer.create(this, R.raw.music)
        player.setVolume(SharedPrefs.getMVol(), SharedPrefs.getMVol())
            player.start()


        //hiding ui
        this.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

}