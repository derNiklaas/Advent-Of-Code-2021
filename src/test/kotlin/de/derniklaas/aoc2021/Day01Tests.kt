package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day01Tests {
    @Test
    public fun part1() {
        val input = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263").mapToInt()
        Assertions.assertEquals(7, Day01(input).part1())
    }

    @Test
    public fun part2() {
        val input = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263").mapToInt()
        Assertions.assertEquals(5, Day01(input).part2())
    }
}
