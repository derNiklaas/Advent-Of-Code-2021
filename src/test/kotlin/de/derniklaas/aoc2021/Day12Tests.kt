package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day12Tests {
    @Test
    public fun part1() {
        val inputs = File("src/test/resources/Day12.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(10, Day12(inputs).part1())

        val inputs2 = File("src/test/resources/Day12-alt.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(19, Day12(inputs2).part1())

        val inputs3 = File("src/test/resources/Day12-alt2.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(226, Day12(inputs3).part1())

    }

    @Test
    public fun part2() {
        val inputs = File("src/test/resources/Day12.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(36, Day12(inputs).part2())

        val inputs2 = File("src/test/resources/Day12-alt.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(103, Day12(inputs2).part2())

        val inputs3 = File("src/test/resources/Day12-alt2.txt").readLines().map {
            val (from, to) = it.split("-"); from to to
        }
        Assertions.assertEquals(3509, Day12(inputs3).part2())
    }
}
