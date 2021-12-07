package de.derniklaas.aoc2021

import java.io.File
import kotlin.math.abs

// o7 in the chat

fun main() {
    val input = File("src/main/resources/Day07.txt").readLines().first().splitAndMapToInt(",").sorted()
    val day07 = Day07(input)
    println("Part 1: ${day07.part1()}")
    println("Part 2: ${day07.part2()}")
}

public class Day07(private val input: List<Int>) {
    public fun part1() = calculateCost()

    public fun part2() = calculateCost(true)

    private fun calculateCost(secondPart: Boolean = false) =
        input.first().rangeTo(input.last()).minOf { alignedPosition ->
            input.sumOf {
                if (secondPart) {
                    val steps = abs(it - alignedPosition)
                    (steps * steps + steps) / 2
                } else {
                    abs(it - alignedPosition)
                }
            }
        }
}
