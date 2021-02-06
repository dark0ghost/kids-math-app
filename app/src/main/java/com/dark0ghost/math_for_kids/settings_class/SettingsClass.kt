package com.dark0ghost.math_for_kids.settings_class

import android.content.SharedPreferences

interface SettingsClass {

    fun save(preferences: SharedPreferences)

    fun init(preferences: SharedPreferences)

}