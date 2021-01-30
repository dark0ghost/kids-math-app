package com.dark0ghost.math_for_kids.math_impl

import android.util.Log
import pl.kremblewski.expressionevaluator.evaluate
import java.math.BigDecimal
import kotlin.properties.Delegates
import kotlin.math.pow


open class MathGenerate {

    private fun <T> List<T>.randomObject(): T = get(random.randomNumber(0, lastIndex+1))

    private var begin by Delegates.notNull<Int>()

    private var end by Delegates.notNull<Int>()

    private var len: Int = 0

    private var lastOperation: MathOperation = MathOperation.Plus

    private var arrayOperation: List<MathOperation> = listOf()

    private var lastNumber = 0

    private val random: ObjectRandom = ObjectRandom()

    private val tag: String = "Math/"

    private fun generateMathExample(operation: List<MathOperation>, arrayNumber: List<Int>): String {
        var result: String = ""
        for (_i in 0..len)
            result += marker(operation.randomObject(), arrayNumber.randomObject())
        if( lastOperation !=MathOperation.Division)
            result += arrayNumber.randomObject().toString()
        else
            result += getNotZeroValue()

        Log.d(tag + "generateMathExample", result)
        return result
    }

    private fun generateNumberArray(): List<Int> {
        val result = mutableListOf<Int>()
        val lenGen = if (len < 10) 10 else len
        for (_i in 0 until lenGen)
            result.add(random.randomNumber(begin, end))
        Log.d(tag + "generateNumberArray", result.toString())
        return result.shuffled()
    }

    private fun getNotZeroValue(): Int {
        var value = random.randomNumber(begin, end)
        while (value.isZero()) {
            value = random.randomNumber(begin, end)
            Log.d(tag + "getNotZeroValue", value.toString())
        }
        Log.d(tag + "getNotZeroValue", value.toString())
        return value
    }

    private fun getNumberForIntegerResult(): Int {
        var value = random.randomNumber(begin, end)
        if (value.isZero()) value = getNotZeroValue()
        new@while (!lastNumber.isIntegerResult(value)) {
            value = random.randomNumber(begin, end)
            if (value.isZero()) value = 1
            Log.d(tag + "getNumberForIntegerRes", value.toString())
            if (lastNumber == value && !value.isPrimeNumber)
                continue@new
        }
        Log.d(tag + "getNumberForIntegerRes", value.toString())
        return value
    }

    private fun getRandomOperationAndNotDivision(): MathOperation {
        var operation = arrayOperation.randomObject()
        while (operation == MathOperation.Division) {
            operation = arrayOperation.randomObject()
            Log.d("${tag}getRandomOperation", operation.toString())
        }
        Log.d("${tag}getRandomOperation", operation.toString())
        return operation
    }

    private fun <T : Number> isInteger(v: T): Boolean = "." !in v.toString()

    private fun marker(operation: MathOperation, number: Int): String {
        if (operation == MathOperation.Division && number.isZero()) {
            lastOperation = operation
            lastNumber = getNotZeroValue()
            return (if (lastNumber > 0) lastNumber.toString() else "($lastNumber)") + operation.operation
        }
        if (operation == MathOperation.Division && !lastNumber.isIntegerResult(number)) {
            lastOperation = operation
            val i  = getNumberForIntegerResult()
            lastNumber = i
            return (if (i > 0) i.toString() else "($i)") + operation.operation
        }
        if (operation == MathOperation.Division && lastOperation == operation) {
            lastOperation = getRandomOperationAndNotDivision()
            lastNumber = number
            return (if (number > 0) number.toString() else "($number)") +  lastOperation.operation
        }
        lastOperation = operation
        lastNumber = number
        return (if (number > 0) number.toString() else "($number)") + operation.operation
    }

    private fun getAnswerOnExample(example: String): BigDecimal = evaluate(example)

    open fun getData(operation: List<MathOperation>, begin: Int, end: Int, len: Int = 2): Pair<String, BigDecimal> {
        this.begin = begin
        this.end = end
        this.arrayOperation = operation.shuffled()
        this.len = len
        var mathExample: String
        var answer: BigDecimal?
        do {
            val numberArray = generateNumberArray()
            mathExample = generateMathExample(arrayOperation.shuffled(), numberArray.shuffled())
            answer = try {
                getAnswerOnExample(mathExample)
            }catch (e: StackOverflowError){
                null
            }
            Log.d(tag + "generate", "---")

        } while (answer == null || !isInteger(answer))
        val u: Int = 10
        Log.d(tag + "main", mathExample)
        return Pair(mathExample, answer)

    }
    private companion object{
         fun Int.isZero(): Boolean = (this == 0)

         fun Int.isIntegerResult(number: Int): Boolean = (this % number) == 0

         val Int.isPrimeNumber: Boolean
             get() = 2.toDouble().pow(this) % this == 1.0
    }

}

