package de.derniklaas.aoc2021

import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/main/resources/Day02.txt").readLines()
    val day02 = Day02(input)
    println("Part 1: ${day02.part1()}")
    println("Part 2: ${day02.part2()}")
}


public class Day02(private val input: List<String>) {

    private var currentPoint: Point2D = Point2D(0, 0)
    public fun part1(): Int {
        currentPoint = Point2D(0, 0)
        for (line in input) {
            val (direction, distance) = line.split(" ")
            val amount = distance.toInt()
            currentPoint = when (direction) {
                "forward" -> Point2D(currentPoint.x + amount, currentPoint.y)
                "down" -> Point2D(currentPoint.x, currentPoint.y - amount)
                "up" -> Point2D(currentPoint.x, currentPoint.y + amount)
                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
        }
        return abs(currentPoint.x * currentPoint.y)
    }

    public fun part2(): Int {
        currentPoint = Point2D(0, 0)
        var aim = 0
        for (line in input) {
            val (direction, distance) = line.split(" ")
            val amount = distance.toInt()
            when (direction) {
                "forward" -> currentPoint = Point2D(currentPoint.x + amount, currentPoint.y - amount * aim)
                "down" -> aim -= amount
                "up" -> aim += amount
                else -> throw IllegalArgumentException("Unknown direction: $direction")
            }
        }
        return abs(currentPoint.x * currentPoint.y)
    }
}

private data class Point2D(val x: Int, val y: Int)
