package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day04.txt").readLines().filter { it.isNotEmpty() && it.isNotBlank() }
    val day04 = Day04(input)
    println("Part 1: ${day04.part1()}")
    println("Part 2: ${day04.part2()}")
}


public class Day04(private val input: List<String>) {
    private val finishedNumbers = mutableListOf<Int>()

    init {
        // Read drawn numbers
        val numbers = input.first().splitAndMapToInt(",")

        // Set up boards
        val boards = mutableListOf<BingoBoard>()

        input.drop(1).chunked(5).forEach { board ->
            boards += board.map { line ->
                line.splitAndMapToInt().map { BingoField(it, false) }
            }
        }

        // Solve all boards
        var remainingCards = boards.toList()
        for (number in numbers) {
            for (board in remainingCards) {
                board.markNumber(number)
                if (board.hasBingo()) finishedNumbers += board.calculateScore() * number
            }
            remainingCards = remainingCards.filter { !it.hasBingo() }
        }
    }

    public fun part1() = finishedNumbers[0]

    public fun part2() = finishedNumbers.last()

    /** Checks if this board has a bingo (ignoring diagonals bingos). */
    private fun BingoBoard.hasBingo(): Boolean {
        for (i in 0 until 5) {
            if (this[i].all { it.drawn }) return true
            if (this[0][i].drawn && this[1][i].drawn && this[2][i].drawn && this[3][i].drawn && this[4][i].drawn) return true
        }
        return false
    }

    /** Calculate the score of this board. */
    private fun BingoBoard.calculateScore() = this.sumOf {
        it.sumOf { field ->
            if (!field.drawn) field.number else 0
        }
    }

    private fun BingoBoard.markNumber(number: Int) {
        for (row in this) {
            for (field in row) {
                if (field.number == number) {
                    field.drawn = true
                }
            }
        }
    }
}

private data class BingoField(val number: Int, var drawn: Boolean)

private typealias BingoBoard = List<List<BingoField>>
