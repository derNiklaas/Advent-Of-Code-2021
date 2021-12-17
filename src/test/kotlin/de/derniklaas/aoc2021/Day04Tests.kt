package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day04Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day04.txt").readLines().filter { it.isNotEmpty() && it.isNotBlank() }
        val day04 = Day04(inputs)
        Assertions.assertEquals(4512, day04.part1())
    }

    @Test
    public fun part2() {
        val inputs = File("src/test/resources/Day04.txt").readLines().filter { it.isNotEmpty() && it.isNotBlank() }
        val day04 = Day04(inputs)
        Assertions.assertEquals(1924, day04.part2())
    }

}
