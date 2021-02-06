package com.dark0ghost.math_for_kids.settings

import android.content.SharedPreferences
import com.dark0ghost.math_for_kids.settings_class.SettingsClass
import java.math.BigDecimal

object Settings: SettingsClass {

    lateinit var beginRange: BigDecimal

    lateinit var endRange: BigDecimal

    lateinit var lenMathExample: BigDecimal

    override fun init(preferences: SharedPreferences) {
        beginRange = preferences.getString("begin_range", "0")!!.toBigDecimal()
        endRange = preferences.getString("begin_range", "100")!!.toBigDecimal()
        lenMathExample = preferences.getString("len_math_example", "1")!!.toBigDecimal()
    }

    override fun save(preferences: SharedPreferences){
        val edit:  SharedPreferences.Editor = preferences.edit()

        edit.apply()
    }
}