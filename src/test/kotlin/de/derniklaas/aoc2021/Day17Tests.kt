package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day17Tests {

    @Test
    public fun part1() {
        Assertions.assertEquals(45, Day17("target area: x=20..30, y=-10..-5").solve().first)
    }

    @Test
    public fun part2() {
        Assertions.assertEquals(112, Day17("target area: x=20..30, y=-10..-5").solve().second)
    }
}