package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day18.txt").readLines()
    val day18 = Day18(input)
    println("Part 1: ${day18.part1()}")
    println("Part 2: ${day18.part2()}")
}

public class Day18(input: List<String>) {

    private val parsedExpressions = input.map { parseInput(StringBuilder(it)) }

    public fun part1() = parsedExpressions.reduce { a, b -> a.add(b) }.calculateMagnitude()

    public fun part2(): Int {
        val magnitudes = mutableListOf<Int>()
        for (i in parsedExpressions.indices) {
            for (j in parsedExpressions.indices) {
                if (i == j) continue
                magnitudes += parsedExpressions[i].add(parsedExpressions[j]).calculateMagnitude()
            }
        }

        return magnitudes.maxOrNull()!!
    }

    private fun parseInput(input: StringBuilder): Expression {
        // Check if we are starting a pair in the format [x,y]
        // where x and y can be either numbers or other expressions
        if (input.startsWith("[")) {
            input.deletePrefix(1) // remove [
            val left = parseInput(input) // remove the x part
            require(input.startsWith(",")) { "Expected ',' but found '${input.first()}'" }
            input.deletePrefix(1) // remove ,
            val right = parseInput(input) // remove the y part
            require(input.startsWith("]")) { "Expected ']' but found '${input.first()}'" }
            input.deletePrefix(1) // remove ]
            return Expression.Pair(left, right)
        }

        // Parsing for numbers with n digits.
        var value = ""
        while (input[0] in '0'..'9') {
            value += input.takeAndDelete(1)
        }

        require(value.isNotEmpty()) { "Expected number but couldn't find anything" }

        return Expression.Value(value.toInt())
    }
}

public sealed class Expression {
    // I hate data classes, and they don't give the right value. If you know why, please let me know.

    public class Value(val value: Int) : Expression() {
        override fun toString() = "$value"
    }

    public class Pair(val left: Expression, val right: Expression) : Expression() {
        override fun toString() = "[$left,$right]"
    }

    public fun add(other: Expression): Expression =
        generateSequence<Expression>(Pair(this, other)) { s -> s.reduceOperation()?.let { s.replace(it) } }.last()

    /** Recursive algorithm to find a pair with a [depth]. */
    private fun findPair(depth: Int): Pair? {
        if (depth == 0) return this as? Pair?

        if (this is Pair) {
            left.findPair(depth - 1)?.let { return it }
            right.findPair(depth - 1)?.let { return it }
        }
        return null
    }

    /** Recursive algorithm to find a value where x >= [limit]. */
    private fun findValue(limit: Int): Value? = when (this) {
        is Value -> {
            if (value >= limit) this else null
        }
        is Pair -> {
            left.findValue(limit)?.let { return it }
            right.findValue(limit)?.let { return it }
            null
        }
    }

    private fun traverse(stop: Pair): List<Expression> = when (this) {
        is Value -> listOf(this)
        is Pair -> {
            if (this == stop) listOf(this)
            else left.traverse(stop) + right.traverse(stop)
        }
    }

    public fun reduceOperation(): Map<Expression, Expression>? {
        val pair = findPair(4) // find a pair with depth 4
        if (pair != null) {
            check(pair.left is Value)
            check(pair.right is Value)
            val operation = mutableMapOf<Expression, Expression>(pair to Value(0))
            val path = traverse(pair)
            val index = path.indexOf(pair)
            (path.getOrNull(index - 1) as? Value)?.let {
                operation[it] = Value(it.value + pair.left.value)
            }
            (path.getOrNull(index + 1) as? Value)?.let {
                operation[it] = Value(it.value + pair.right.value)
            }
            return operation
        }
        val value = findValue(10) // Find a value where x >= 10
        if (value != null) {
            return mapOf(
                value to Pair(
                    Value(value.value / 2),
                    Value((value.value + 1) / 2)
                )
            )
        }

        return null
    }

    public fun replace(operation: Map<Expression, Expression>): Expression {
        operation[this]?.let { return it }
        return when (this) {
            is Value -> this
            is Pair -> Pair(left.replace(operation), right.replace(operation))
        }
    }

    public fun calculateMagnitude(): Int = when (this) {
        is Value -> value
        is Pair -> 3 * left.calculateMagnitude() + 2 * right.calculateMagnitude()
    }
}
