package com.dark0ghost.math_for_kids.score

import android.content.SharedPreferences


object Score{
    private lateinit var  prefs: SharedPreferences

    private lateinit var  editor:  SharedPreferences.Editor

    private var localScore: Int = 0

    fun setSharedPreferences(pref: SharedPreferences){
        prefs = pref

    }

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

    var maxScore: Int = prefs.getInt("max score",0)
    private set

    val arrayScore: MutableList<String> = prefs.getString("all score","0")?.split("")?.toMutableList()?: mutableListOf("0")


    fun save(): Score{
        editor.putString("all score",arrayScore.toString())
        editor.putInt("max score", localScore)
        return this@Score
    }
}