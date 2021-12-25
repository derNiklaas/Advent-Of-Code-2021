package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day05.txt").readLines()
    val day05 = Day05(input)
    println("Part 1: ${day05.part1()}")
    println("Part 2: ${day05.part2()}")
}

public class Day05(input: List<String>) {
    private val lines = input.map { toLine(it) }
    private val grid: Array<Array<Int>>

    init {
        val maxX = lines.maxOf { it.maxX }
        val maxY = lines.maxOf { it.maxY }

        grid = Array(maxY + 1) { Array(maxX + 1) { 0 } }
    }

    public fun part1(): Int {
        for (line in lines) {
            // Only consider horizontal and vertical lines
            if (!(line.start.x == line.end.x || line.start.y == line.end.y)) continue

            for (x in line.minX..line.maxX) {
                for (y in line.minY..line.maxY) {
                    grid[y][x]++
                }
            }
        }

        return grid.sumOf { it.count { number -> number >= 2 } }
    }

    public fun part2(): Int {
        for (line in lines) {
            // Only calculate diagonal lines, horizontal and vertical lines have already been counted in part1
            if (line.start.x == line.end.x || line.start.y == line.end.y) continue

            val maxX = line.maxX
            val minX = line.minX
            val directionX = if (line.start.x > line.end.x) -1 else 1
            val directionY = if (line.start.y > line.end.y) -1 else 1

            // dx and dy have to be the same, otherwise the line is not at an 45° angle
            val steps = maxX - minX
            for (i in 0..steps) {
                grid[line.start.y + i * directionY][line.start.x + i * directionX]++
            }
        }

        return grid.sumOf { it.count { number -> number >= 2 } }
    }

    /**
     *  Turns a String from the input into a line
     *  Example: 0,9 -> 5,9 turns into a line from (0,9) to (5,9)
     */
    private fun toLine(input: String): Line {
        val (a, b) = input.split(" -> ")
        val (a1, a2) = a.splitAndMapToInt(",")
        val (b1, b2) = b.splitAndMapToInt(",")
        return Line(Point(a1, a2), Point(b1, b2))
    }
}

private data class Line(public val start: Point, public val end: Point) {
    public val minX = minOf(start.x, end.x)
    public val maxX = maxOf(start.x, end.x)
    public val minY = minOf(start.y, end.y)
    public val maxY = maxOf(start.y, end.y)
}
