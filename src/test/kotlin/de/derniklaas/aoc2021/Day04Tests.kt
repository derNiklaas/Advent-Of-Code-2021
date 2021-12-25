package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day04Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day04.txt").readLines().filter { it.isNotEmpty() && it.isNotBlank() }
        Assertions.assertEquals(4512, Day04(input).part1())
    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day04.txt").readLines().filter { it.isNotEmpty() && it.isNotBlank() }
        Assertions.assertEquals(1924, Day04(input).part2())
    }

}
