package com.dark0ghost.math_for_kids.score

import android.content.SharedPreferences


object Score{
    private lateinit var  prefs: SharedPreferences

    private lateinit var  editor:  SharedPreferences.Editor

    private var localScore: Int = 0

    fun setSharedPreferences(pref: SharedPreferences){
        prefs = pref
        maxScore = prefs.getInt("max score",0)
        arrayScore= prefs.getString("all score","0")?.split("")?.toMutableList()?: mutableListOf("-1")

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

    var maxScore: Int = -1
    private set

    var arrayScore: MutableList<String> =  mutableListOf("0")
    private set


    fun save(): Score{
        editor.putString("all score",arrayScore.toString())
        editor.putInt("max score", localScore)
        return this@Score
    }
}