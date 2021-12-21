package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day21.txt").readLines()
    val day21 = Day21(input)
    println("Part 1: ${day21.part1()}")
    println("Part 2: ${day21.part2()}")
}

public class Day21(input: List<String>) {

    private val startingPositions = input.map { it.split(": ")[1].toInt() }

    private val wins = mutableListOf(0, 0)

    public fun part1(): Int {
        val positions = startingPositions.toMutableList()
        val points = mutableListOf(0, 0)
        var currentDice = 1
        var player = 0
        var rolls = 0
        while (true) {
            var point = positions[player]

            for (i in 0 until 3) {
                rolls++
                point += currentDice
                currentDice++
                if (currentDice > 100) currentDice = 1
            }

            while (point !in 1..10) {
                point -= 10
            }

            //println("$player | $currentDice | $point")

            positions[player] = point
            points[player] += point
            if (points[player] >= 1000) {
                println("Player $player wins!")
                return when (player) {
                    0 -> points[1] * rolls
                    else -> points[0] * rolls
                }
            }

            player = if (player == 0) 1 else 0
        }
    }

    public fun part2(): Int {

        // This will throw a StackOverflowError, have to think of something better.

        //universe(startingPositions[0], startingPositions[1], 0, 0, 1, 1, 0, 0)
        //universe(startingPositions[0], startingPositions[1], 0, 0, 2, 1, 0, 0)
        //universe(startingPositions[0], startingPositions[1], 0, 0, 3, 1, 0, 0)

        return wins.maxOf { it }
    }

    private fun universe(
        positionA: Int,
        positionB: Int,
        pointsA: Int,
        pointsB: Int,
        currentDice: Int,
        player: Int,
        turn: Int,
        turnPoints: Int
    ) {
        var t = turn
        var posA = positionA
        var posB = positionB
        var pA = pointsA
        var pB = pointsB
        while (true) {
            if (turn == 4) {
                var newPoints = turnPoints
                while (newPoints !in 1..10) {
                    newPoints -= 10
                }
                when (player) {
                    0 -> {
                        pA += newPoints
                        posA = newPoints

                        if (pA >= 1000) {
                            wins[0]++
                            return
                        }
                    }
                    else -> {
                        pB += newPoints
                        posB = newPoints
                        if (pB >= 1000) {
                            wins[0]++
                            return
                        }
                    }
                }
                t = if (t == 0) 1 else 0

            }
            var newPoints = when (player) {
                0 -> posA
                else -> posB
            }


            for (i in 0 until 3) {
                newPoints += currentDice
                universe(posA, posB, pA, pB, 1, player, turn + 1, newPoints)
                universe(posA, posB, pA, pB, 2, player, turn + 1, newPoints)
                universe(posA, posB, pA, pB, 3, player, turn + 1, newPoints)
            }
        }
    }

}
