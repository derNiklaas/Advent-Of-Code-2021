package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day07Tests {
    @Test
    public fun part1() {
        val input = "16,1,2,0,4,2,7,1,2,14".splitAndMapToInt(",").sorted()
        Assertions.assertEquals(37, Day07(input).part1())
    }

    @Test
    public fun part2() {
        val input = "16,1,2,0,4,2,7,1,2,14".splitAndMapToInt(",").sorted()
        Assertions.assertEquals(168, Day07(input).part2())
    }
}
