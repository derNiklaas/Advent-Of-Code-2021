package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day02Tests {
    @Test
    public fun part1() {
        val inputs = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        val day02 = Day02(inputs)
        Assertions.assertEquals(150, day02.part1())
    }

    @Test
    public fun part2() {
        val inputs = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        val day02 = Day02(inputs)
        Assertions.assertEquals(900, day02.part2())
    }
}
