package de.derniklaas.aoc2021

import java.io.File
import kotlin.math.abs

public fun main() {
    val input = File("src/main/resources/Day17.txt").readLines().first()
    val day17 = Day17(input)
    val solution = day17.solve()
    println("Part 1: ${solution.first}")
    println("Part 2: ${solution.second}")
}

public class Day17(input: String) {

    private val targetXArea: IntRange
    private val targetYArea: IntRange

    init {
        val (x, y) = input.split(",").map { it.trim().split("=")[1].splitAndMapToInt("..") }
        targetXArea = x.first()..x.last()
        targetYArea = y.first()..y.last()
    }

    public fun solve(): Pair<Int, Int> {
        val validTargets = mutableListOf<Int>()
        for (x in 0..targetXArea.last) {
            for (y in targetYArea.first..abs(targetYArea.first)) {
                val velocity = Point(x, y)
                val pair = simulate(velocity)
                if (pair.first) {
                    validTargets += pair.second
                }
            }
        }

        val max = validTargets.maxOf { it }
        val velocities = validTargets.count()

        return Pair(max, velocities)
    }

    private fun simulate(velocity: Point): Pair<Boolean, Int> {
        val currentPos = Point(0, 0)
        val lowestPossibleY = targetYArea.first
        var highestY = lowestPossibleY - 1

        var xVelocity = velocity.x
        var yVelocity = velocity.y

        while (true) {
            // Check if the current position is in the target area.
            if (currentPos.x in targetXArea && currentPos.y in targetYArea) {
                return Pair(true, highestY)
            }

            // Cancel the current simulation if it's impossible to hit the target area.
            // This is the case if the current y position is below the lowest possible target y value.
            if (currentPos.y < lowestPossibleY) {
                return Pair(false, 0)
            }

            // Add the current velocity to the current position.
            currentPos += Point(xVelocity, yVelocity)

            // If the current position is higher than the highest possible target y value,
            // change the new highest y value to the current y value.
            if (currentPos.y > highestY) {
                highestY = currentPos.y
            }

            // If possible, change the x velocity by 1 towards ``0``.
            if (xVelocity > 0) {
                xVelocity--
            } else if (xVelocity < 0) {
                xVelocity++
            }
            // Decrease y velocity by 1 due to gravity.
            yVelocity--
        }

    }

}
