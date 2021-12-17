package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day13.txt").readLines()
    val day13 = Day13(input)
    println("Part 1: ${day13.part1()}")
    println("Part 2: ${day13.part2()}")
}

public class Day13(input: List<String>) {

    private var card: List<Point>
    private val instructions: List<DirectionalFoldLine>

    init {
        val c = ArrayList<Point>()
        instructions = ArrayList()
        for (line in input) {
            if (line.isEmpty()) continue
            if (line.startsWith("fold")) {
                val (axis, value) = line.split(" ").last().split("=")
                instructions += if (axis == "y") HorizontalFoldLine(value.toInt())
                else VerticalFoldLine(value.toInt())
                continue
            }
            val (x, y) = line.splitAndMapToInt(",")
            c += Point(x, y)
        }
        card = c.toList()
        println()
    }

    private fun printCard() {
        val maxX = card.maxByOrNull { it.x }!!.x
        val maxY = card.maxByOrNull { it.y }!!.y
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                val point = card.find { it.x == x && it.y == y }
                if (point != null) {
                    print("#")
                } else {
                    print(" ")
                }
            }
            println()
        }
    }

    public fun part1(): Int {
        val card = instructions[0].fold(card)

        //  printCard()

        //println("bad: 796, 589")

        return card.size
    }

    public fun part2(): Int {
        for (instruction in instructions) {
            card = instruction.fold(card)
        }

        printCard()

        return -1
    }
}

public sealed class DirectionalFoldLine {
    abstract fun fold(card: List<Point>): List<Point>
}

public data class HorizontalFoldLine(private val y: Int) : DirectionalFoldLine() {
    override fun fold(card: List<Point>): List<Point> {
        return card.map { point ->
            if (point.y < y) return@map point
            Point(point.x, point.y - 2 * (point.y - y))
        }.distinct()
    }
}

public data class VerticalFoldLine(private val x: Int) : DirectionalFoldLine() {
    override fun fold(card: List<Point>): List<Point> {
        return card.map { point ->
            if (point.x < x) return@map point
            Point(point.x - 2 * (point.x - x), point.y)
        }.distinct()
    }
}
