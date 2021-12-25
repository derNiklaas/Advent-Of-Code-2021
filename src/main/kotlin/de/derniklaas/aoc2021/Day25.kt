package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day25.txt").readLines()
    val day25 = Day25(input)
    println("Part 1: ${day25.part1()}")
    println("Part 2: ${day25.part2()}")
}

public class Day25(input: List<String>) {
    private val firstMap = buildMap {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == 'v') put(Point(x, y), Cumber(true))
                if (char == '>') put(Point(x, y), Cumber(false))
            }
        }
    }

    private val height = input.size
    private val width = input[0].length

    public fun part1(): Int {
        var iterations = 0
        var currentMap = Result(firstMap, true)
        while (currentMap.changed) {
            iterations++
            currentMap = simulate(currentMap.map)
        }
        return iterations
    }

    public fun part2(): String {
        return "Merry Christmas and Happy Holidays!"
    }

    private fun simulate(map: Map<Point, Cumber>): Result {
        var changed = false

        val east = map.filter { !it.value.facingSouth }
        val eastMovers = mutableListOf<Point>()
        for (point in east.keys) {
            if (map[point.eastNeighbor()] == null) {
                eastMovers += point
                changed = true
            }
        }

        val targetMap = map.toMutableMap()

        for (move in eastMovers) {
            targetMap -= move
            targetMap += move.eastNeighbor() to Cumber(false)
        }

        val south = map.filter { it.value.facingSouth }
        val southMovers = mutableListOf<Point>()
        for (point in south.keys) {
            if (targetMap[point.southNeighbor()] == null) {
                southMovers += point
                changed = true
            }
        }

        for (move in southMovers) {
            targetMap -= move
            targetMap += move.southNeighbor() to Cumber(true)
        }

        return Result(targetMap, changed)
    }

    private fun Point.eastNeighbor(): Point {
        return Point((x + 1) % width, y)
    }

    private fun Point.southNeighbor(): Point {
        return Point(x, (y + 1) % height)
    }
}

private data class Cumber(public val facingSouth: Boolean)

private data class Result(public val map: Map<Point, Cumber>, public val changed: Boolean)
