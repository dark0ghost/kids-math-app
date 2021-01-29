package com.dark0ghost.math_for_kids.score

import android.content.SharedPreferences
import kotlin.properties.Delegates

class Score(val prefs: SharedPreferences){
    private var score: Int = prefs.getInt("score",0)



}