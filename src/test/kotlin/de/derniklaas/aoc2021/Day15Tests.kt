package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day15Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day15.txt").readLines().splitAndMapToInt("")
        val nodes = getNodes(input)
        Assertions.assertEquals(40, Day15(nodes).solve())
    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day15.txt").readLines().splitAndMapToInt("")
        val nodes = getNodes(input, 5)
        Assertions.assertEquals(315, Day15(nodes).solve())
    }
}
