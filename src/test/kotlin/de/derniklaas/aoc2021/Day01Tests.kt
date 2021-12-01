package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day01Tests {

    @Test
    public fun part1() {
        val inputs = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263").mapToInt()
        val day01 = Day01(inputs)
        Assertions.assertEquals(7, day01.part1())
    }

    @Test
    public fun part2() {
        val inputs = listOf("199", "200", "208", "210", "200", "207", "240", "269", "260", "263").mapToInt()
        val day01 = Day01(inputs)
        Assertions.assertEquals(5, day01.part2())
    }
}
