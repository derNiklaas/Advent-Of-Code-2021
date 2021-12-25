package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day10Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day10.txt").readLines()
        Assertions.assertEquals(26397, Day10(input).part1())
    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day10.txt").readLines()
        Assertions.assertEquals(288957, Day10(input).part2())
    }
}
