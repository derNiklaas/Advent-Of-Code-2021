package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day09Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day09.txt").readLines().splitAndMapToInt("")
        Assertions.assertEquals(15, Day09(input).part1())
    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day09.txt").readLines().splitAndMapToInt("")
        val day09 = Day09(input)
        day09.part1()
        Assertions.assertEquals(1134, day09.part2())
    }
}
