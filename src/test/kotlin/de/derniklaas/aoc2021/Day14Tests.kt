package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day14Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day14.txt").readLines()
        Assertions.assertEquals(1588, Day14(inputs).part1())
    }

    @Test
    public fun part2() {
        val inputs = File("src/test/resources/Day14.txt").readLines()
        Assertions.assertEquals(2188189693529, Day14(inputs).part2())
    }
}
