package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day08Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day08.txt").readLines()
        Assertions.assertEquals(26, Day08(input).part1())
    }

    @Test
    public fun part2() {
        val results = listOf(8394, 9781, 1197, 9361, 4873, 8418, 4548, 1625, 8717, 4315)

        val input = File("src/test/resources/Day08.txt").readLines()
        for (i in input.indices) {
            val result = Day08(listOf(input[i])).part2()
            Assertions.assertEquals(result, results[i])
        }
    }
}
