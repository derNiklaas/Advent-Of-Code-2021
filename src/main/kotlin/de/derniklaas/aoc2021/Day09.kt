package de.derniklaas.aoc2021

import java.io.File

fun main() {
    val input = File("src/main/resources/Day09.txt").readLines().splitAndMapToInt("")
    val day09 = Day09(input)
    println("Part 1: ${day09.part1()}")
    println("Part 2: ${day09.part2()}")
}

public class Day09(private val input: List<List<Int>>) {
    private var basins = mutableListOf<Pair<Int, Int>>()
    private var visited = Array(input.size) { Array(input[0].size) { false } }

    public fun part1(): Int {
        var points = 0

        for (i in input.indices) {
            for (j in input[0].indices) {
                var lowestPoint = true

                // The biggest one-digit number is 9, so there can't be a smaller one.
                if (input[i][j] == 9) continue

                if (i - 1 in input.indices) { // Up
                    if (input[i - 1][j] <= input[i][j]) {
                        lowestPoint = false
                    }
                }
                if (i + 1 in input.indices) { // Down
                    if (input[i + 1][j] <= input[i][j]) {
                        lowestPoint = false
                    }
                }
                if (j - 1 in input[0].indices) { // Left
                    if (input[i][j - 1] <= input[i][j]) {
                        lowestPoint = false
                    }
                }
                if (j + 1 in input[0].indices) { // Right
                    if (input[i][j + 1] <= input[i][j]) {
                        lowestPoint = false
                    }
                }
                if (lowestPoint) {
                    points += input[i][j] + 1
                    basins += Pair(i, j)
                }
            }
        }

        return points
    }

    public fun part2(): Int {

        val basinSizes = mutableListOf<Int>()

        for ((i, j) in basins) {
            basinSizes += getSize(i, j)
        }

        val sizes = basinSizes.sortedByDescending { it }
        return sizes[0] * sizes[1] * sizes[2];
    }

    private fun getSize(i: Int, j: Int): Int {
        // Ignore already visited points
        if (visited[i][j]) {
            return 0
        }

        // Mark point as visited
        visited[i][j] = true

        var sum = 1

        // Check all directions
        if (i - 1 in input.indices) { // Up
            if (input[i - 1][j] != 9) {
                sum += getSize(i - 1, j)
            }
        }
        if (i + 1 in input.indices) { // Down
            if (input[i + 1][j] != 9) {
                sum += getSize(i + 1, j)
            }
        }
        if (j - 1 in input[0].indices) { // Left
            if (input[i][j - 1] != 9) {
                sum += getSize(i, j - 1)
            }
        }
        if (j + 1 in input[0].indices) { // Right
            if (input[i][j + 1] != 9) {
                sum += getSize(i, j + 1)
            }
        }

        return sum
    }
}
