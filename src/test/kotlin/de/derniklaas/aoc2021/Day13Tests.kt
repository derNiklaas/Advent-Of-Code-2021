package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day13Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day13.txt").readLines()
        Assertions.assertEquals(17, Day13(inputs).part1())
    }
}
