package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day25Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day25.txt").readLines()
        Assertions.assertEquals(58, Day25(input).part1())
    }
}
