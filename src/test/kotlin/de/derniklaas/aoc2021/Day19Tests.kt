package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day19Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day19.txt").readLines()
        Assertions.assertEquals(79, Day19(inputs).part1())
    }

    @Test
    public fun part2() {
        val inputs = File("src/test/resources/Day19.txt").readLines()
        Assertions.assertEquals(3621, Day19(inputs).part2())
    }
}
