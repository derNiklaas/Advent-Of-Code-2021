package de.derniklaas.aoc2021

import java.io.File
import kotlin.math.abs

/*

PLEASE DO NOT RUN THIS CODE FOR PART 2. THIS IS SUPER, SUPER SLOW. [30-40 minutes]
BUT IT WORKS... SO I'LL JUST LEAVE IT HERE.

 */

fun main() {
    val input = File("src/main/resources/Day15.txt").readLines().splitAndMapToInt("")
    val nodesP1 = getNodes(input)
    println("Part 1: ${Day15(nodesP1).solve()}")
    val nodesP2 = getNodes(input, 5)
    println("Part 2: ${Day15(nodesP2).solve()}")
}

public fun getNodes(input: List<List<Int>>, scale: Int = 1): List<Node> {
    val nodes = mutableListOf<Node>()
    repeat(input.size * scale) { y ->
        repeat(input[0].size * scale) { x ->
            nodes += getNode(x, y, input)
        }
    }
    return nodes.toList()
}

private fun getValueForNode(x: Int, y: Int, input: List<List<Int>>): Int {
    val realX = x % input[0].size
    val realY = y % input.size

    val blockX = (x / input[0].size)
    val blockY = (y / input.size)

    if (blockX == 0 && blockY == 0) return input[realY][realX]

    val oldValue = when {
        blockX > 0 -> getValueForNode(x - input[0].size, y, input)
        blockY > 0 -> getValueForNode(x, y - input.size, input)
        else -> error("This should not happen")
    }

    var newValue = oldValue + 1
    if (newValue == 10) newValue = 1

    return newValue
}

public fun getNode(x: Int, y: Int, input: List<List<Int>>): Node {
    return Node(x, y, getValueForNode(x, y, input))
}

public class Day15(private val input: List<Node>) {

    public fun solve(): Int {
        val startNode = input.first()
        val endNode = input.last()
        val path = dijkstra(input, startNode, endNode)
        return path.sumOf { it.cost } - startNode.cost
    }

    private fun dijkstra(graph: List<Node>, start: Node, target: Node): ArrayDeque<Node> {
        var steps = 0L
        val queue = mutableSetOf<Node>()
        val distances = mutableMapOf<Node, Int?>()
        val previous = mutableMapOf<Node, Node?>()
        for (vortex in graph) {
            distances[vortex] = Int.MAX_VALUE
            previous[vortex] = null
            queue += vortex
        }
        distances[start] = 0
        while (queue.isNotEmpty()) {
            if (steps % 100L == 0L) println("Size: ${queue.size}")
            steps++
            val currentNode = queue.minByOrNull { distances[it]!! }!!
            queue -= currentNode
            if (currentNode == target) break

            for (neighbour in queue.filter { it.isNeighbour(currentNode) }) {
                val alt = distances[currentNode]!! + neighbour.cost
                if (alt < distances[neighbour]!!) {
                    distances[neighbour] = alt
                    previous[neighbour] = currentNode
                }
            }
        }
        val path = ArrayDeque<Node>()
        var currentNode: Node? = target
        if (previous[currentNode] != null) {
            while (currentNode != null) {
                path.addFirst(currentNode)
                currentNode = previous[currentNode]
            }
        }
        return path
    }
}

public data class Node(public val x: Int, public val y: Int, public val cost: Int) {
    public fun isNeighbour(node: Node): Boolean {
        val dx = abs(x - node.x)
        val dy = abs(y - node.y)
        return dx + dy == 1
    }
}
