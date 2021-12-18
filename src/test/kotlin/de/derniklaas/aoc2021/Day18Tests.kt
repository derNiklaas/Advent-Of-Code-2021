package de.derniklaas.aoc2021

import java.io.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

public class Day18Tests {
    @Test
    public fun part1() {
        Assertions.assertEquals(143, Day18(listOf("[[1,2],[[3,4],5]]")).part1())
        Assertions.assertEquals(1384, Day18(listOf("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")).part1())
        Assertions.assertEquals(445, Day18(listOf("[[[[1,1],[2,2]],[3,3]],[4,4]]")).part1())
        Assertions.assertEquals(791, Day18(listOf("[[[[3,0],[5,3]],[4,4]],[5,5]]")).part1())
        Assertions.assertEquals(1137, Day18(listOf("[[[[5,0],[7,4]],[5,5]],[6,6]]")).part1())
        Assertions.assertEquals(3488, Day18(listOf("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")).part1())
        Assertions.assertEquals(4140, Day18(File("src/test/resources/Day18.txt").readLines()).part1())
    }

    @Test
    public fun part2() {
        Assertions.assertEquals(3993, Day18(File("src/test/resources/Day18.txt").readLines()).part2())
    }
}
