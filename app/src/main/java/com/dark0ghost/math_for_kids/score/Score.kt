package com.dark0ghost.math_for_kids.score

import android.content.SharedPreferences
import com.dark0ghost.math_for_kids.settings_class.SettingsClass


object Score: SettingsClass{

    private var localScore: Int = 0

    fun updateScore(){
        localScore++
        if(localScore > maxScore) {
            maxScore = localScore
        }
    }

    val score
        get() = localScore

    fun clearScore(){
        arrayScore.add(localScore.toString())
        localScore = 0
    }

    var maxScore: Int = -1
    private set

    var arrayScore: MutableList<String> =  mutableListOf("0")
    private set

    override fun save(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putString("all score",arrayScore.toString())
        editor.putInt("max score", maxScore)
        editor.apply()
    }

    override fun init(preferences: SharedPreferences) {
        maxScore = preferences.getInt("max score",0)
        arrayScore = preferences.getString("all score","0")?.split("")?.toMutableList()?: mutableListOf("-1")
    }
}