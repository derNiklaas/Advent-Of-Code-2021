package de.derniklaas.aoc2021

import java.io.File

fun main() {
    val input = File("src/main/resources/Day04.txt").readLines()
    val day04 = Day04(input)
    println("Part 1: ${day04.part1()}")
    day04.reset()
    println("Part 2: ${day04.part2()}")
}


public class Day04(private val input: List<String>) {
    private var numbers = listOf<Int>()
    private val boards = mutableListOf<Array<Array<BingoField>>>()

    init {
        reset()
    }

    fun reset() {
        boards.clear()
        var currentX = 0
        var currentBoard = Array(5) {
            Array(5) { BingoField(-1, false) }
        }
        for (line in input) {
            if (line.isEmpty()) continue
            if (line.isBlank()) continue

            if (line.contains(",")) {
                numbers = line.split(",").mapToInt()
                continue
            }

            // fill board with numbers
            currentBoard[currentX] =
                line.split(" ").filter(String::isNotEmpty).mapToInt().map { BingoField(it, false) }.toTypedArray()
            currentX++

            // if out of board: reset board creation
            if (currentX == 5) {
                currentX = 0
                boards += currentBoard
                currentBoard = Array(5) {
                    Array(5) { BingoField(-1, false) }
                }
                println("Created new board")
            }
        }
    }

    public fun part1(): Int {
        for (number in numbers) {
            for (board in boards) {
                for (row in board) {
                    for (field in row) {
                        if (field.number == number) {
                            field.drawn = true
                        }
                    }
                }
            }
            for (board in boards) {
                if (hasBingo(board)) {
                    val sum = board.sumOf {
                        it.sumOf { field ->
                            if (!field.drawn) field.number else 0
                        }
                    }
                    return sum * number
                }
            }
        }
        return 0
    }

    public fun part2(): Int {
        var winningBoard = -1;
        for (number in numbers) {
            for (board in boards) {
                for (row in board) {
                    for (field in row) {
                        if (field.number == number) {
                            field.drawn = true
                        }
                    }
                }
            }
            if (boards.filter { !hasBingo(it) }.size == 1) {
                winningBoard = boards.indexOf(boards.first { !hasBingo(it) })
            }
            if (boards.filter { !hasBingo(it) }.isEmpty()) {
                val sum = boards[winningBoard].sumOf {
                    it.sumOf { field ->
                        if (!field.drawn) field.number else 0
                    }
                }
                return sum * number
            }
        }
        return 0
    }

    private fun hasBingo(board: Array<Array<BingoField>>): Boolean {
        for (i in 0 until 5) {
            if (board[i].all { it.drawn }) return true
            if (board[0][i].drawn && board[1][i].drawn && board[2][i].drawn && board[3][i].drawn && board[4][i].drawn) return true
        }
        return false
    }

    public fun printBoards() {
        for (board in boards) {
            for (row in board) {
                for (field in row) {
                    print("${if (field.drawn) "🎉" else "😩"} ")
                }
                println()
            }
            println()
        }
    }
}

private data class BingoField(val number: Int, var drawn: Boolean) {

    override fun equals(other: Any?): Boolean {
        return other is BingoField && other.number == number
    }

    override fun hashCode(): Int {
        return number
    }
}
