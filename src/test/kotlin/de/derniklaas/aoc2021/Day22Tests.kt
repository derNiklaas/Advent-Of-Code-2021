package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day22Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day22A.txt").readLines()
        Assertions.assertEquals(590784, Day22(input).part1())
    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day22B.txt").readLines()
        Assertions.assertEquals(2758514936282235, Day22(input).part2())
    }
}
