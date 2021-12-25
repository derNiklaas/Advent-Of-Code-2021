package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day02Tests {
    @Test
    public fun part1() {
        val input = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        Assertions.assertEquals(150, Day02(input).part1())
    }

    @Test
    public fun part2() {
        val input = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        Assertions.assertEquals(900, Day02(input).part2())
    }
}
