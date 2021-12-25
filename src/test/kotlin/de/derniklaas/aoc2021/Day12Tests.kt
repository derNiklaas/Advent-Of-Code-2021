package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day12Tests {
    @Test
    public fun part1() {
        val input = File("src/test/resources/Day12.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(10, Day12(input).part1())

        val input2 = File("src/test/resources/Day12-alt.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(19, Day12(input2).part1())

        val input3 = File("src/test/resources/Day12-alt2.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(226, Day12(input3).part1())

    }

    @Test
    public fun part2() {
        val input = File("src/test/resources/Day12.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(36, Day12(input).part2())

        val input2 = File("src/test/resources/Day12-alt.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(103, Day12(input2).part2())

        val input3 = File("src/test/resources/Day12-alt2.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(3509, Day12(input3).part2())
    }
}
