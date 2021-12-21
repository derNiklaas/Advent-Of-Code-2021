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

    private val wins = mutableListOf(0L, 0L)

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
                return when (player) {
                    0 -> points[1] * rolls
                    else -> points[0] * rolls
                }
            }

            player = if (player == 0) 1 else 0
        }
    }

    public fun part2(): Long {
        val queue = ArrayDeque<GameState>()

        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 3, true, 1)
        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 4, true, 3)
        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 5, true, 6)
        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 6, true, 7)
        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 7, true, 6)
        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 8, true, 3)
        queue += GameState(startingPositions[0], startingPositions[1], 0, 0, 9, true, 1)

        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            var positionA = state.positionA
            var positionB = state.positionB
            var pointsA = state.pointsA
            var pointsB = state.pointsB
            var player = state.firstPlayer
            when (player) {
                true -> {
                    var points = state.currentDice + positionA
                    while (points !in 1..10) {
                        points -= 10
                    }
                    pointsA += points
                    positionA = points

                    if (pointsA >= 21) {
                        wins[0] += state.wins
                        continue
                    }
                }
                false -> {
                    var points = state.currentDice + positionB
                    while (points !in 1..10) {
                        points -= 10
                    }
                    pointsB += points
                    positionB = points
                    if (pointsB >= 21) {
                        wins[1] += state.wins
                        continue
                    }
                }
            }
            player = !player

            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 3, player, state.wins))
            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 4, player, state.wins * 3))
            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 5, player, state.wins * 6))
            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 6, player, state.wins * 7))
            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 7, player, state.wins * 6))
            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 8, player, state.wins * 3))
            queue.addFirst(GameState(positionA, positionB, pointsA, pointsB, 9, player, state.wins))

        }

        return wins.maxOf { it }
    }
}

private data class GameState(
    public val positionA: Int,
    public val positionB: Int,
    public val pointsA: Int,
    public val pointsB: Int,
    public val currentDice: Int,
    public val firstPlayer: Boolean,
    public val wins: Long
)

// Probability of numbers with three, three sided dices

// Number: |   3   |   4   |   5   |   6   |   7   |   8   |   9   |
// Amount: |   1   |   3   |   6   |   7   |   6   |   3   |   1   |

// 1 + 1 + 1 = 3

// 1 + 1 + 2 = 4
// 1 + 2 + 1 = 4
// 2 + 1 + 1 = 4

// 1 + 1 + 3 = 5
// 1 + 2 + 2 = 5
// 1 + 3 + 1 = 5
// 2 + 1 + 2 = 5
// 2 + 2 + 1 = 5
// 3 + 1 + 1 = 5

// 1 + 2 + 3 = 6
// 1 + 3 + 2 = 6
// 2 + 1 + 3 = 6
// 2 + 2 + 2 = 6
// 2 + 3 + 1 = 6
// 3 + 1 + 2 = 6
// 3 + 2 + 1 = 6

// 1 + 3 + 3 = 7
// 2 + 2 + 3 = 7
// 2 + 3 + 2 = 7
// 3 + 1 + 3 = 7
// 3 + 2 + 2 = 7
// 3 + 3 + 1 = 7

// 2 + 3 + 3 = 8
// 3 + 2 + 3 = 8
// 3 + 3 + 2 = 8

// 3 + 3 + 3 = 9
