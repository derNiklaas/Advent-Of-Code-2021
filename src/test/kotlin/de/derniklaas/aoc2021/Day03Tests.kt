package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day03Tests {
    @Test
    public fun part1() {
        val inputs = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )
        val day03 = Day03(inputs)
        Assertions.assertEquals(198, day03.part1())
    }

    @Test
    public fun part2() {
        val inputs = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
        )
        val day03 = Day03(inputs)
        Assertions.assertEquals(230, day03.part2())
    }
}
