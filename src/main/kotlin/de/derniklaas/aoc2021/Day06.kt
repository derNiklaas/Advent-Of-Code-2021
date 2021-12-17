package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day06.txt").readLines().first().splitAndMapToInt(",")
    val day06 = Day06(input)
    println("Part 1: ${day06.part1()}")
    println("Part 2: ${day06.part2()}")
}

public class Day06(input: List<Int>) {

    private var fish = mutableMapOf<Int, Long>()

    init {
        input.forEach {
            fish[it] = (fish[it] ?: 0) + 1
        }
    }

    public fun part1(): Long {
        for (i in 0 until 80) {
            advanceDay()
        }
        return fish.values.sum()
    }

    public fun part2(): Long {
        for (i in 0 until (256 - 80)) {
            advanceDay()
        }
        return fish.values.sum()
    }

    private fun advanceDay() {
        val nextIteration = mutableMapOf<Int, Long>()

        nextIteration[8] = fish[0] ?: 0L
        nextIteration[7] = fish[8] ?: 0L
        nextIteration[6] = (fish[7] ?: 0) + (fish[0] ?: 0)
        nextIteration[5] = fish[6] ?: 0
        nextIteration[4] = (fish[5] ?: 0)
        nextIteration[3] = (fish[4] ?: 0)
        nextIteration[2] = (fish[3] ?: 0)
        nextIteration[1] = (fish[2] ?: 0)
        nextIteration[0] = (fish[1] ?: 0)

        fish = nextIteration
    }
}
