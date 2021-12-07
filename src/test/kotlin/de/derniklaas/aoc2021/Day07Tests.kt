package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day07Tests {

    @Test
    public fun part1() {
        val inputs = "16,1,2,0,4,2,7,1,2,14".splitAndMapToInt(",").sorted()
        val day07 = Day07(inputs)
        Assertions.assertEquals(37, day07.part1())
    }

    @Test
    public fun part2() {
        val inputs = "16,1,2,0,4,2,7,1,2,14".splitAndMapToInt(",").sorted()
        val day07 = Day07(inputs)
        Assertions.assertEquals(168, day07.part2())
    }
}
