package com.izzzya.fortunewheel

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    init {
        sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    }
    companion object {
        private var sharedPref: SharedPreferences? = null
        private const val PREFERENCES = "prefs"
        private const val MUSIC_VOL: Float = 1F
        private const val SOUND_VOL: Float = 1F
        private const val COINS: Int = 0
        private const val CURRENT_COINS: Int = 0

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


        fun getCoins(): Int{
            return sharedPref?.getInt("COINS", 0)!!
        }

        fun setCoins(c: Int){
            val newCoins = getCoins() + c
            sharedPref?.edit()
                ?.putInt("COINS", newCoins)
                ?.apply()
        }

        fun getCurCoins(): Int{
            return sharedPref?.getInt("CURRENT_COINS", 0)!!
        }

        fun setCurCoins(c: Int){
            val newCoins = getCoins() + c
            sharedPref?.edit()
                ?.putInt("CURRENT_COINS", newCoins)
                ?.apply()
        }


    }
}