package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day24.txt").readLines()
    val day24 = Day24(input)
    val solution = day24.solve()
    println("Part 1: ${solution.first}")
    println("Part 2: ${solution.second}")
}

/*

Easy solution thanks to Reddit users: /u/i_have_no_biscuits and /u/etotheipi1
Thanks for sharing!

 */

public class Day24(input: List<String>) {
    private val parameters = parseParameters(input)

    public fun solve(): Pair<Long, Long> {
        var zValues = mutableMapOf(0L to (0L to 0L))
        parameters.forEach { parameter ->
            val currentZValues = mutableMapOf<Long, Pair<Long, Long>>()
            zValues.forEach { (z, minMax) ->
                (1..9).forEach { digit ->
                    val newValue = solve(parameter, z, digit.toLong())
                    if (parameter.a == 1 || (parameter.a == 26 && newValue < z)) {
                        val max = maxOf(currentZValues[newValue]?.first ?: Long.MIN_VALUE, minMax.second * 10 + digit)
                        val min = minOf(currentZValues[newValue]?.second ?: Long.MAX_VALUE, minMax.first * 10 + digit)
                        currentZValues[newValue] = max to min
                    }
                }
            }
            zValues = currentZValues
        }
        return zValues.getValue(0)
    }

    private fun solve(parameter: Parameters, z: Long, w: Long) =
        if (z % 26 + parameter.b != w) {
            ((z / parameter.a) * 26) + w + parameter.c
        } else {
            z / parameter.a
        }

    private fun parseParameters(input: List<String>): List<Parameters> = input.chunked(18).map {
        Parameters(
            it[4].substringAfterLast(" ").toInt(),
            it[5].substringAfterLast(" ").toInt(),
            it[15].substringAfterLast(" ").toInt()
        )
    }
}

private class Parameters(val a: Int, val b: Int, val c: Int)
