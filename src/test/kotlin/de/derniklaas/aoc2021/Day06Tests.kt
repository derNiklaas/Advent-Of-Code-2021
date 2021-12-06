package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day06Tests {

    @Test
    public fun part1() {
        val inputs = "3,4,3,1,2".splitAndMapToInt(",")
        val day06 = Day06(inputs)
        Assertions.assertEquals(5934, day06.part1())
    }

    @Test
    public fun part2() {
        val inputs = "3,4,3,1,2".splitAndMapToInt(",")
        val day06 = Day06(inputs)
        day06.part1()
        Assertions.assertEquals(26984457539, day06.part2())
    }
}
