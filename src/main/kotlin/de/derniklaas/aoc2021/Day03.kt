package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day03.txt").readLines()
    val day03 = Day03(input)
    println("Part 1: ${day03.part1()}")
    println("Part 2: ${day03.part2()}")
}

public class Day03(private val input: List<String>) {

    public fun part1(): Int {
        val map = generateMap(input)

        var mostCommonString = ""
        var leastCommonString = ""

        for (i in 0 until input[0].length) {
            if (map[i]?.toDouble()!! / input.size >= 0.5) {
                mostCommonString += "1"
                leastCommonString += "0"
            } else {
                mostCommonString += "0"
                leastCommonString += "1"
            }
        }

        return leastCommonString.toInt(2) * mostCommonString.toInt(2)
    }

    public fun part2(): Int {
        var possibleOxygenRatings = input
        var possibleCO2Ratings = input
        for (i in 0 until input[0].length) {
            if (possibleOxygenRatings.size != 1) {
                possibleOxygenRatings = filter(possibleOxygenRatings, i)
            }
            if (possibleCO2Ratings.size != 1) {
                possibleCO2Ratings = filter(possibleCO2Ratings, i, false)
            }
        }

        return possibleOxygenRatings[0].toInt(2) * possibleCO2Ratings[0].toInt(2)
    }

    private fun filter(input: List<String>, position: Int, oxygen: Boolean = true): List<String> {
        val map = generateMap(input)
        val needed = if (map[position]!!.toDouble() / input.size >= 0.5) {
            if (oxygen) '1' else '0'
        } else {
            if (oxygen) '0' else '1'
        }
        return input.filter { it[position] == needed }
    }

    private fun generateMap(input: List<String>): Map<Int, Int> {
        val map = mutableMapOf<Int, Int>()
        input.forEach { line ->
            line.split("").forEachIndexed { index, char ->
                if (char == "1") {
                    map[index - 1] = map[index - 1]?.plus(1) ?: 1
                }
            }
        }
        return map.toMap()
    }
}