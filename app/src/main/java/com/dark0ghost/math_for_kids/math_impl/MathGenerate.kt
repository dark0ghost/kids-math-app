package com.dark0ghost.math_for_kids.math_impl

import pl.kremblewski.expressionevaluator.evaluate
import java.math.BigDecimal
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

open class MathGenerate {
    private fun Int.isZero(): Boolean = this == 0

    private fun Int.isIntegerResult(number: Int): Boolean = (this / number) % 1.0 == 0.0

    private var begin by Delegates.notNull<Int>()

    private var end by Delegates.notNull<Int>()

    private  var lastOperation: MathOperation = MathOperation.Plus

    private var arrayOperation: List<MathOperation> = listOf()

    private var lastNumber = 0

    private fun javaRandom(): Int = java.util.Random().nextInt((end - begin) + 1) + begin

    private fun generateMathExample(operation: List<MathOperation>, arrayNumber: List<Int>): String {
        var result: String = arrayNumber.random().toString()
        for (_i in arrayNumber.indices)
            result += marker(operation.shuffled().random(), arrayNumber.shuffled().random())
        return result
    }

    private fun generateNumberArray(len: Int): List<Int> {
        val result = mutableListOf<Int>()
        result.add(Random.nextInt(begin..end))
        for (_i in 1 until len)
            result.add(javaRandom())
        return result.shuffled()
    }

    private fun getNotZeroValue(): Int {
        var value = Random.nextInt(begin..end)
        while (value.isZero())
            value = javaRandom()
        return value
    }

    private fun getNumberForIntegerResult(): Int {
        var value = Random.nextInt(begin..end)
        while (!lastNumber.isIntegerResult(value) && lastNumber != value)
            value = javaRandom()
        return value
    }

    private fun getRandomOperationAndNotDivision(): MathOperation {
        var operation = arrayOperation.random()
        while (operation == MathOperation.Division)
            operation = arrayOperation.shuffled().random()
        return operation
    }

    private fun <T: Number> isInteger(v: T): Boolean = "." !in v.toString()

    private fun marker(operation: MathOperation,number: Int): String {
        if (operation == MathOperation.Division && number.isZero()) {
            lastNumber = number
            return operation.operation + getNotZeroValue().toString()
        }
        if(operation == MathOperation.Division && !lastNumber.isIntegerResult(number)){
            lastNumber = number
            return operation.operation + getNumberForIntegerResult().toString()
        }
        if(operation == MathOperation.Division && lastOperation == operation){
            return getRandomOperationAndNotDivision().operation + number.toString()
        }
        lastNumber = number
        return operation.operation + number.toString()
    }

    private fun getAnswerOnExample(example: String): BigDecimal = evaluate(example)

    open fun getData(operation: List<MathOperation>, begin: Int, end: Int,len: Int = 2): Pair<String,BigDecimal> {
        this.begin = begin
        this.end = end
        this.arrayOperation = operation.shuffled()
        var mathExample: String
        var answer: BigDecimal
        do {
            val numberArray = generateNumberArray(len)
            mathExample = generateMathExample(arrayOperation.shuffled(), numberArray.shuffled())
            answer = getAnswerOnExample(mathExample)
        }while (!isInteger(answer))
        return Pair(mathExample, answer)
    }
}