package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day08.txt").readLines()
    val day08 = Day08(input)
    println("Part 1: ${day08.part1()}")
    println("Part 2: ${day08.part2()}")
}

public class Day08(private val input: List<String>) {

    public fun part1(): Int {

        val outputs = input.map { it.split(" | ")[1].trim().split(" ") }

        return outputs.sumOf { line ->
            line.count {
                it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7
            }
        }
    }

    public fun part2(): Int {
        val lines = input.map { line ->
            val (input, output) = line.split(" | ")
            input.split(" ") to output.split(" ")
        }

        return lines.sumOf { (input, output) ->
            getConfiguration(input, output)
        }
    }

    private fun getConfiguration(inputs: List<String>, outputs: List<String>): Int {
        val chars = ('a'..'g').toSet()
        val inputCables = (0..6).toSet().allPermutations()

        fun getMapping(): Map<Char, Int> {
            permute@ for (permutation in inputCables) {
                val map = chars.zip(permutation).toMap()
                for (input in inputs) {
                    val mapped = input.map { map[it] }.toSet()
                    // Check if the segment is valid
                    if (!segmentsToDigit.containsKey(mapped)) {
                        continue@permute
                    }
                }
                return map
            }
            throw IllegalStateException("No valid mapping found")
        }

        val mapping = getMapping()
        val number = outputs.joinToString("") { code ->
            val segment = code.map { mapping[it] }.toSet()
            "${segmentsToDigit[segment]}"
        }

        return number.toInt()
    }

    //  0000
    // 1    2
    // 1    2
    //  3333
    // 4    5
    // 4    5
    //  6666

    private val segmentsToDigit = mapOf(
        setOf(0, 1, 2, 4, 5, 6) to 0,
        setOf(2, 5) to 1,
        setOf(0, 2, 3, 4, 6) to 2,
        setOf(0, 2, 3, 5, 6) to 3,
        setOf(1, 2, 3, 5) to 4,
        setOf(0, 1, 3, 5, 6) to 5,
        setOf(0, 1, 3, 4, 5, 6) to 6,
        setOf(0, 2, 5) to 7,
        setOf(0, 1, 2, 3, 4, 5, 6) to 8,
        setOf(0, 1, 2, 3, 5, 6) to 9
    )

}
