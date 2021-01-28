package com.dark0ghost.math_for_kids

import kotlin.random.Random

open class MathGenerate {
    private val validOperation: List<String> = listOf("+","-","*","/")

    private fun generateMathExample(operation: List<String>, arrayNumber: List<Int>): String{
        var result: String = arrayNumber.random().toString()
        for(i in arrayNumber.indices)
            result += operation.random(Random) + arrayNumber.random(Random).toString()
        return result
    }



    private fun generateNumberArray(begin: Int,end: Int,len: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (_i in 0 until len)
            result.add(Random.nextInt(begin,end))
        return result
    }

    private fun getAnswerOnExample(example: String): Int = TODO()

    open fun getData(operation: List<String>, begin: Int, end: Int):Pair<String,Int>{
        val operationValid = operation.filter {
            it in validOperation
        }
        val numberArray = generateNumberArray(begin,end,operationValid .size * 2)
        val mathExample = generateMathExample(operationValid ,numberArray)
        val answer = getAnswerOnExample(mathExample)
        return Pair(mathExample,answer)
    }

}