package de.derniklaas.aoc2021

import java.io.File

fun main() {
    val input = File("src/main/resources/Day12.txt").readLines().map {
        val (from, to) = it.split("-"); from to to
    }
    val day12 = Day12(input)
    println("Part 1: ${day12.part1()}")
    println("Part 2: ${day12.part2()}")
}


public class Day12(input: List<Pair<String, String>>) {

    private val nodes = input.flatMap { it.toList() }.distinct().sorted()

    private val transitions = mutableMapOf<String, MutableList<String>>()

    init {
        for ((from, to) in input) {
            transitions.getOrPut(from) { mutableListOf() }.add(to)
            transitions.getOrPut(to) { mutableListOf() }.add(from)
        }
    }

    public fun part1(): Int {
        val targetSet = mutableSetOf<List<String>>()
        findPaths(listOf("start"), targetSet)

        return targetSet.size
    }

    public fun part2(): Int {
        val targetSet = mutableSetOf<List<String>>()

        for (smallCaves in nodes.filter { it.isLowerCase() && it !in listOf("start", "end") }) {
            findPaths(listOf("start"), targetSet, smallCaves)
        }

        return targetSet.size
    }

    private fun findPaths(
        currentPath: List<String>,
        targetSet: MutableSet<List<String>>,
        specialBigCave: String? = null
    ) {
        val currentNode = currentPath.last()
        // Check if we are at the end of the cave
        if (currentNode == "end") {
            targetSet += currentPath
            return
        }
        // Get all possible transitions from the current node
        val nextNodes = transitions[currentNode]!!
        // Filter all transitions that are not allowed / we've already visited (twice)
        val candidates = nextNodes.filter {
            it.isUpperCase() || it !in (currentPath + "start") || (it == specialBigCave && currentPath.count { it == specialBigCave } <= 1)
        }
        // Recurse for all candidates
        for (candidate in candidates) {
            findPaths(currentPath + candidate, targetSet, specialBigCave)
        }
    }
}
