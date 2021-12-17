package de.derniklaas.aoc2021

import java.io.File

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
        val (x, y) = input.split(",").map { it.trim().split("=")[1].split("..") }
        targetXArea = x.first().toInt()..x.last().toInt()
        targetYArea = y.first().toInt()..y.last().toInt()
    }

    public fun solve(): Pair<Int, Int> {
        val validTargets = mutableMapOf<Point, Int>()
        // x/y values tweaked, so it's faster & still gives the correct example.
        // If you don't get the right result, tweak the values.
        // Initially I did [-1000;1000], but that was too slow
        for (x in -200..200) {
            for (y in -200..200) {
                val velocity = Point(x, y)
                val pair = simulate(velocity)
                if (pair.first) {
                    validTargets += velocity to pair.second
                }
            }
        }

        val max = validTargets.maxOf { it.value }
        val velocities = validTargets.count()

        return Pair(max, velocities)
    }

    private fun simulate(velocity: Point): Pair<Boolean, Int> {
        val currentPos = Point(0, 0)
        val lowestPossibleY = targetYArea.minOf { it }
        var highestY = lowestPossibleY - 1

        var xVelocity = velocity.x
        var yVelocity = velocity.y

        while (true) {
            // Check if the point is in the target area

            if (currentPos.x in targetXArea && currentPos.y in targetYArea) {
                return Pair(true, highestY)
            }

            // Below target zone, literally impossible.
            if (currentPos.y < lowestPossibleY) {
                return Pair(false, 0)
            }

            currentPos += Point(xVelocity, yVelocity)
            if (currentPos.y > highestY) {
                highestY = currentPos.y
            }

            if (xVelocity > 0) {
                xVelocity--
            } else if (xVelocity < 0) {
                xVelocity++
            }
            yVelocity--

            //println(currentPos)
            //println("$xVelocity, $yVelocity")

        }

    }

}
