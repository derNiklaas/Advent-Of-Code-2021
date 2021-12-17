package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day10.txt").readLines()
    val day10 = Day10(input)
    println("Part 1: ${day10.part1()}")
    println("Part 2: ${day10.part2()}")
}

public class Day10(private val input: List<String>) {

    public fun part1(): Int {
        return input.sumOf { this.getFirstIllegalCharacter(it) }
    }

    public fun part2(): Long {
        val input2 = input.filter { this.getFirstIllegalCharacter(it) == 0 }.map { fixString(it) }.sorted()
        val length = input2.size / 2
        return input2[length]
    }

    private fun getFirstIllegalCharacter(value: String): Int {
        val reducedValue = reduceString(value)

        if (reducedValue.contains("{)") || reducedValue.contains("[)") || reducedValue.contains("<)")) {
            return 3
        }
        if (reducedValue.contains("{]") || reducedValue.contains("(]") || reducedValue.contains("<]")) {
            return 57
        }
        if (reducedValue.contains("(}") || reducedValue.contains("[}") || reducedValue.contains("<}")) {
            return 1197
        }
        if (reducedValue.contains("{>") || reducedValue.contains("(>") || reducedValue.contains("[>")) {
            return 25137
        }

        return 0
    }

    private fun fixString(value: String): Long {
        var sum = 0L
        var reducedString = reduceString(value)
        while (reducedString.isNotEmpty()) {
            sum *= 5
            when (val lastChar = reducedString.last()) {
                '(' -> {
                    sum += 1
                    reducedString += ")"
                }
                '[' -> {
                    sum += 2
                    reducedString += "]"
                }
                '{' -> {
                    sum += 3
                    reducedString += "}"
                }
                '<' -> {
                    sum += 4
                    reducedString += ">"
                }
                else -> {
                    throw IllegalStateException("Unknown character: $lastChar")
                }
            }
            reducedString = reduceString(reducedString)
        }
        return sum
    }

    private fun reduceString(value: String): String {
        var currentIteration: String
        var previousIteration = value
        while (true) {
            currentIteration = previousIteration.replace("()", "").replace("[]", "").replace("{}", "").replace("<>", "")
            if (currentIteration == previousIteration) {
                break
            }
            previousIteration = currentIteration
        }
        return currentIteration
    }
}
