package com.dark0ghost.math_for_kids.math_impl

import com.google.android.material.chip.ChipDrawable
import pl.kremblewski.expressionevaluator.evaluate
import java.math.BigDecimal
import kotlin.properties.Delegates
import kotlin.random.Random

open class MathGenerate {
    private val validOperation: List<String> = listOf("+", "-", "*", "/")

    private fun Int.isZero(): Boolean = this == 0

    private fun Int.isIntegerResult(number: Int): Boolean = (this / number) % 1.0 == 0.0

    private var begin by Delegates.notNull<Int>()
    private var end by Delegates.notNull<Int>()

    private var lastNumber = 0

    private fun generateMathExample(operation: List<String>, arrayNumber: List<Int>): String {
        var result: String = arrayNumber.random().toString()
        for (i in arrayNumber.indices)
            result += marker(operation.random(Random), arrayNumber.random(Random))
        return result
    }

    private fun generateNumberArray(len: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (_i in 0 until len)
            result.add(Random.nextInt(begin, end))
        return result
    }

    private fun getNotZeroValue(): Int {
        var value = Random.nextInt(begin, end)
        while (value.isZero())
            value = Random.nextInt(begin, end)
        return value
    }

    private fun getNumberForIntegerResult(): Int {
        var value = Random.nextInt(begin, end)
        while (!lastNumber.isIntegerResult(value))
            value = Random.nextInt(begin, end)
        return value
    }

    private fun <T: Number> isInteger(v: T): Boolean = "." !in v.toString()


    private fun marker(operation: String,number: Int): String {
        if (operation == MathOperation.Division.operation && number.isZero()) {
            lastNumber = number
            return operation + getNotZeroValue().toString()
        }
        if(operation == MathOperation.Division.operation &&  !lastNumber.isIntegerResult(number)){
            lastNumber = number
            return operation + getNumberForIntegerResult().toString()
        }
        lastNumber = number
        return operation+number.toString()
    }

    private fun getAnswerOnExample(example: String): BigDecimal = evaluate(example)

    open fun getData(operation: List<String>, begin: Int, end: Int): Pair<String,BigDecimal> {
        val operationValid = operation.filter {
            it in validOperation
        }
        this.begin = begin
        this.end = end
        var mathExample: String
        var answer: BigDecimal
        do {
            val numberArray = generateNumberArray(operationValid.size * 2)
            mathExample = generateMathExample(operationValid, numberArray)
            answer = getAnswerOnExample(mathExample)
        }while (!isInteger(answer))
        return Pair(mathExample, answer)
    }
}