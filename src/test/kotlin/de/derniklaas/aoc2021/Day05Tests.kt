package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day05Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day05.txt").readLines()
        Assertions.assertEquals(5, Day05(inputs).part1())
    }

    @Test
    public fun part2() {
        val inputs = File("src/test/resources/Day05.txt").readLines()
        val day05 = Day05(inputs)
        day05.part1()
        Assertions.assertEquals(12, day05.part2())
    }
}
