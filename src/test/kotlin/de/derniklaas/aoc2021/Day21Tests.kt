package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day21Tests {
    @Test
    public fun part1() {
        Assertions.assertEquals(739785, Day21(listOf(": 4", ": 8")).part1())
    }

    @Test
    public fun part2() {
        Assertions.assertEquals(444356092776315, Day21(listOf(": 4", ": 8")).part2())
    }
}
