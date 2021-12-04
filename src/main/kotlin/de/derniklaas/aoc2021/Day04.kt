package de.derniklaas.aoc2021

import java.io.File

fun main() {
    val input = File("src/main/resources/Day04.txt").readLines().filter { it.isNotEmpty() && it.isNotBlank() }
    val day04 = Day04(input)
    println("Part 1: ${day04.part1()}")
    day04.resetBoards()
    println("Part 2: ${day04.part2()}")
}


public class Day04(private val input: List<String>) {
    private var numbers = listOf<Int>()
    private val boards = mutableListOf<BingoBoard>()

    init {
        resetBoards()
    }

    fun resetBoards() {
        boards.clear()
        var currentX = 0
        var currentBoard = Array(5) {
            Array(5) { BingoField(-1, false) }
        }
        for (line in input) {
            // Get all drawn numbers in order
            if (line.contains(",")) {
                numbers = line.splitAndMapToInt(",")
                continue
            }

            // Fill the board with numbers
            currentBoard[currentX] =
                line.splitAndMapToInt().map { BingoField(it, false) }.toTypedArray()
            currentX++

            // Create a new Board if we reached the end of the current one
            if (currentX == 5) {
                currentX = 0
                boards += currentBoard
                currentBoard = Array(5) {
                    Array(5) { BingoField(-1, false) }
                }
            }
        }
    }

    public fun part1(): Int {
        for (number in numbers) {
            advanceBoards(number)
            for (board in boards) {
                if (hasBingo(board)) {
                    return calculateBoard(board) * number
                }
            }
        }
        return 0
    }

    public fun part2(): Int {
        // Position of the final board
        var finalBoard = -1
        for (number in numbers) {
            advanceBoards(number)

            // Get the information about the last board
            if (boards.filter { !hasBingo(it) }.size == 1) {
                finalBoard = boards.indexOf(boards.first { !hasBingo(it) })
            }

            // Calculate the winner board
            if (boards.none { !hasBingo(it) }) {
                return calculateBoard(boards[finalBoard]) * number
            }
        }
        return 0
    }

    /** Checks off tiles with the drawn [number]. */
    private fun advanceBoards(number: Int) {
        for (board in boards) {
            for (row in board) {
                for (field in row) {
                    if (field.number == number) {
                        field.drawn = true
                    }
                }
            }
        }
    }

    /** Checks if the [board] has a bingo (ignoring diagonals bingos). */
    private fun hasBingo(board: BingoBoard): Boolean {
        for (i in 0 until 5) {
            if (board[i].all { it.drawn }) return true
            if (board[0][i].drawn && board[1][i].drawn && board[2][i].drawn && board[3][i].drawn && board[4][i].drawn) return true
        }
        return false
    }

    /** Calculate the score of a given [board]. */
    private fun calculateBoard(board: BingoBoard) = board.sumOf {
        it.sumOf { field ->
            if (!field.drawn) field.number else 0
        }
    }
}

private data class BingoField(val number: Int, var drawn: Boolean)

private typealias BingoBoard = Array<Array<BingoField>>
