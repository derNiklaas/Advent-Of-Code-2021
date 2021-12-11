package de.derniklaas.aoc2021

import java.io.File

fun main() {
    val input = File("src/main/resources/Day11.txt").readLines().splitAndMapToInt("").map { it.toMutableList() }
    val day11 = Day11(input)
    println("Part 1: ${day11.part1()}")
    println("Part 2: ${day11.part2()}")
}

public class Day11(private val input: List<MutableList<Int>>) {
    public fun part1(): Int {
        val copy = List(input.size) { MutableList(input[0].size) { 0 } }
        for (i in input.indices) {
            for (j in input[0].indices) {
                copy[i][j] = input[i][j]
            }
        }
        return simulate(copy, 100)
    }

    public fun part2(): Int {
        return simulate(input)
    }

    private fun simulate(input: List<MutableList<Int>>, maxStep: Int = 0): Int {
        var step = 0
        var flashes = 0

        while (true) {
            step++
            val queue = ArrayDeque<Pair<Int, Int>>()
            for (i in input.indices) {
                for (j in input[0].indices) {
                    input[i][j] += 1
                    if (input[i][j] == 10) {
                        queue.addLast(Pair(i, j))
                    }
                }
            }

            val flashed = mutableSetOf<Pair<Int, Int>>()

            while (queue.isNotEmpty()) {
                val (i, j) = queue.removeFirst()
                input[i][j] = 0
                if (Pair(i, j) in flashed) continue
                flash(input, flashed, queue, i, j)
            }

            flashes += flashed.size
            if (maxStep != 0 && step == maxStep) {
                return flashes
            }
            if (flashed.size == input.size * input[0].size) {
                return step
            }
        }
    }

    private fun flash(
        input: List<MutableList<Int>>,
        flashed: MutableSet<Pair<Int, Int>>,
        queue: ArrayDeque<Pair<Int, Int>>,
        i: Int,
        j: Int
    ) {
        input[i][j] = 0
        flashed += Pair(i, j)
        for (dx in -1..1) {
            for (dy in -1..1) {
                val x = i + dx
                val y = j + dy

                if (x !in input.indices || y !in input[0].indices) continue

                if (Pair(x, y) in flashed) continue

                input[x][y] += 1
                if (input[x][y] == 10) {
                    queue.addLast(Pair(x, y))
                }
            }
        }
    }


    public fun printList() {
        for (i in input.indices) {
            for (j in input[0].indices) {
                print(input[i][j])
            }
            println()
        }
    }

}
