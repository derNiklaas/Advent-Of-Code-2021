package de.derniklaas.aoc2021

import java.io.File

fun main() {
    val input = File("src/main/resources/Day14.txt").readLines()
    val day14 = Day14(input)
    println("Part 1: ${day14.part1()}")
    println("Part 2: ${day14.part2()}")
}

public class Day14(private val input: List<String>) {

    private val start = input.first()

    private val rules = input.subList(2, input.size).map { it.split(" -> ") }.map { Pair(it[0], it[1]) }

    public fun part1() = simulate(10)

    public fun part2() = simulate(40)

    private fun simulate(generations: Int): Long {
        val start = start.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

        var current = start

        repeat(generations) {
            current = current.flatMap { (key, value) ->
                val rule = rules.find { it.first == key }!!
                listOf("${key[0]}${rule.second}" to value, "${rule.second}${key[1]}" to value)
            }.groupingBy { it.first }.fold(0L) { a, e -> a + e.second }
        }

        val characterMap = current.toList().flatMap { (key, value) -> listOf(key[0] to value, key[1] to value) }
            .groupingBy { it.first }.fold(0L) { acc, element -> acc + element.second }.toMutableMap()
        characterMap[this.start.first()] = characterMap[this.start.first()]!! + 1
        characterMap[this.start.last()] = characterMap[this.start.last()]!! + 1
        val correctedCharMap = characterMap.map { it.value / 2 }

        return correctedCharMap.maxOfOrNull { it }!! - correctedCharMap.minOfOrNull { it }!!
    }
}
