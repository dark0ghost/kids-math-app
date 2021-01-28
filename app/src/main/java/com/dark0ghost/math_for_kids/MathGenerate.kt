package com.dark0ghost.math_for_kids

import kotlin.random.Random

class MathGenerate<T> {
    private fun get_anwser(){

    }

    private fun generate_two_number(begin: Int,end: Int):List<Int> {
        val result = mutableListOf<Int>()
        result.add(Random.nextInt(begin,end))
        result.add(Random.nextInt(begin,end))
        return result
    }
    fun get_data(){

    }
}