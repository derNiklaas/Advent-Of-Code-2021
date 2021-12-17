package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day11Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day11.txt").readLines().splitAndMapToInt("").map { it.toMutableList() }
        Assertions.assertEquals(1656, Day11(inputs).part1())
    }

    @Test
    public fun part2() {
        val inputs = File("src/test/resources/Day11.txt").readLines().splitAndMapToInt("").map { it.toMutableList() }
        Assertions.assertEquals(195, Day11(inputs).part2())
    }
}
