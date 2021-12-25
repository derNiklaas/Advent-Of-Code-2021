package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day20Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day20.txt").readLines()
        Assertions.assertEquals(35, Day20(input, true).part1())
    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day20.txt").readLines()
        Assertions.assertEquals(3351, Day20(input, true).part2())
    }
}
