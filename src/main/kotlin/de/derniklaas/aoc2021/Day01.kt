package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day01.txt").readLines().mapToInt()
    val day01 = Day01(input)
    println("Part 1: ${day01.part1()}")
    println("Part 2: ${day01.part2()}")
}

public class Day01(private val input: List<Int>) {

    public fun part1(): Int {
        var previous = input[0]
        val times = input.subList(1, input.size).count {
            val result = it > previous
            previous = it
            result
        }
        return times
    }

    public fun part2(): Int {
        var times = 0
        for (i in 3 until input.size) {
            val sum = input[i] + input[i - 1] + input[i - 2]
            val previousSum = input[i - 1] + input[i - 2] + input[i - 3]
            if (sum > previousSum) {
                times++
            }
        }
        return times
    }
}
