package de.derniklaas.aoc2021

import java.io.File
import kotlin.math.abs

// o7 in the chat

fun main() {
    val input = File("src/main/resources/Day07.txt").readLines().first().splitAndMapToInt(",")
    val day07 = Day07(input)
    println("Part 1: ${day07.part1()}")
    println("Part 2: ${day07.part2()}")
}

public class Day07(private val input: List<Int>) {
    public fun part1() = calculateCost()

    public fun part2() = calculateCost(true)

    private fun calculateCost(secondPart: Boolean = false): Int {
        var smallestCost = Int.MAX_VALUE
        val smallestPos = input.minOf { it }
        val largestPos = input.maxOf { it }

        for (alignedPosition in smallestPos..largestPos) {
            val cost = input.sumOf {
                val sum = if (secondPart) {
                    val steps = abs(it - alignedPosition)
                    (steps * steps + steps) / 2
                } else {
                    abs(it - alignedPosition)
                }

                sum
            }
            if (cost < smallestCost) smallestCost = cost
        }

        return smallestCost
    }

}
