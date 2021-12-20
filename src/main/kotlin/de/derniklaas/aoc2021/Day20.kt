package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day20.txt").readLines()
    val day20 = Day20(input, true)
    println("Part 1: ${day20.part1()}")
    println("Part 2: ${day20.part2()}")
}

public class Day20(input: List<String>, private val ignoreBackground: Boolean = false) {

    private var translationTable = input[0]
    private var grid = mutableListOf<Pixel>()

    init {
        input.drop(2).forEachIndexed { x, row ->
            row.forEachIndexed { y, char ->
                grid += if (char == '#') {
                    Pixel(Point(x, y), true)
                } else {
                    Pixel(Point(x, y), false)
                }
            }
        }
    }

    public fun part1(): Int {
        var newGrid = grid.toList()
        repeat(2) {
            newGrid = enhance(newGrid, if (!ignoreBackground) it % 2 == 1 else false)
        }
        return newGrid.count { it.lit }
    }

    public fun part2(): Int {
        var newGrid = grid.toList()
        repeat(50) {
            newGrid = enhance(newGrid, if (!ignoreBackground) it % 2 == 1 else false)
        }
        return newGrid.count { it.lit }
    }

    private fun drawGrid(grid: List<Pixel>) {
        val minX = grid.minByOrNull { it.point.x }!!.point.x
        val maxX = grid.maxByOrNull { it.point.x }!!.point.x
        val minY = grid.minByOrNull { it.point.y }!!.point.y
        val maxY = grid.maxByOrNull { it.point.y }!!.point.y
        for (x in minX..maxX) {
            for (y in minY..maxY) {
                val point = Point(x, y)
                val pixel = grid.find { it.point == point }
                if (pixel != null && pixel.lit) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
    }

    private fun enhance(lastStep: List<Pixel>, isBackgroundLit: Boolean): List<Pixel> {
        val minX = lastStep.minByOrNull { it.point.x }!!.point.x - 1
        val maxX = lastStep.maxByOrNull { it.point.x }!!.point.x + 1
        val minY = lastStep.minByOrNull { it.point.y }!!.point.y - 1
        val maxY = lastStep.maxByOrNull { it.point.y }!!.point.y + 1

        val nextStep = mutableListOf<Pixel>()

        for (x in minX..maxX) {
            for (y in minY..maxY) {
                val index = countPixels(lastStep, x, y, isBackgroundLit)
                val char = translationTable[index]
                nextStep += if (char == '#') {
                    Pixel(Point(x, y), true)
                } else {
                    Pixel(Point(x, y), false)
                }
            }
        }
        return nextStep
    }

    private fun countPixels(step: List<Pixel>, x: Int, y: Int, isBackgroundLit: Boolean): Int {
        var binary = ""
        for (dx in -1..1) {
            for (dy in -1..1) {
                val pixel = step.find { it.point == Point(x + dx, y + dy) }
                binary += if (pixel != null && pixel.lit || pixel == null && isBackgroundLit) {
                    "1"
                } else {
                    "0"
                }
            }
        }
        return binary.toInt(2)
    }
}

public data class Pixel(val point: Point, val lit: Boolean)
