package com.izzzya.fortunewheel

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    init {
        sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    }
    companion object {
        private var sharedPref: SharedPreferences? = null
        const val PREFERENCES = "prefs"
        const val MUSIC_VOL: Float = 1F
        const val SOUND_VOL: Float = 1F

        fun getMVol(): Float{
            return sharedPref?.getFloat("MUSIC_VOL", 1F)!!
        }

        fun getSVol(): Float{
            return sharedPref?.getFloat("SOUND_VOL", 1F)!!
        }

        fun setMVol(v: Float){
            sharedPref?.edit()
            ?.putFloat("MUSIC_VOL", v)
            ?.apply()
        }

        fun setSVol(v: Float){
            sharedPref?.edit()
                ?.putFloat("SOUND_VOL", v)
                ?.apply()
        }


    }
}