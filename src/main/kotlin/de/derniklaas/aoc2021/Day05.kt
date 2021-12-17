package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day05.txt").readLines().map { Line.toLine(it) }
    val day05 = Day05(input)
    println("Part 1: ${day05.part1()}")
    println("Part 2: ${day05.part2()}")
}

public class Day05(private val input: List<Line>) {

    private val grid: Array<Array<Int>>

    init {
        val maxX = input.maxOf { if (it.start.x > it.end.x) it.start.x else it.end.x }
        val maxY = input.maxOf { if (it.start.y > it.end.y) it.start.y else it.end.y }

        grid = Array(maxY + 1) { Array(maxX + 1) { 0 } }
    }

    public fun part1(): Int {
        for (line in input) {
            // Only consider horizontal and vertical lines
            if (!(line.start.x == line.end.x || line.start.y == line.end.y)) continue

            val maxX = if (line.start.x > line.end.x) line.start.x else line.end.x
            val maxY = if (line.start.y > line.end.y) line.start.y else line.end.y
            val minX = if (line.start.x < line.end.x) line.start.x else line.end.x
            val minY = if (line.start.y < line.end.y) line.start.y else line.end.y

            for (x in minX..maxX) {
                for (y in minY..maxY) {
                    grid[y][x]++
                }
            }
        }

        return grid.sumOf { it.count { number -> number >= 2 } }

    }

    public fun part2(): Int {

        for (line in input) {
            // Only calculate diagonal lines, horizontal and vertical lines have already been counted in part1
            if (line.start.x == line.end.x || line.start.y == line.end.y) continue

            val maxX = if (line.start.x > line.end.x) line.start.x else line.end.x
            val minX = if (line.start.x < line.end.x) line.start.x else line.end.x
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
}

public data class Line(public val start: Point, public val end: Point) {
    public companion object {
        // Example: 0,9 -> 5,9
        public fun toLine(input: String): Line {
            val (a, b) = input.split(" -> ")
            val (a1, a2) = a.splitAndMapToInt(",")
            val (b1, b2) = b.splitAndMapToInt(",")
            return Line(Point(a1, a2), Point(b1, b2))
        }
    }
}
