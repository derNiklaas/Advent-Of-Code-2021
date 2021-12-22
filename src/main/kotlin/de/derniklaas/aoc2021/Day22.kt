package de.derniklaas.aoc2021

import java.io.File
import kotlin.math.max
import kotlin.math.min

public fun main() {
    val input = File("src/main/resources/Day22.txt").readLines()
    val day22 = Day22(input)
    println("Part 1: ${day22.part1()}")
    println("Part 2: ${day22.part2()}")
}

public class Day22(input: List<String>) {
    private val rules = List(input.size) { i ->
        val (x, y, z) = input[i].split(" ")[1].split(",").map {
            val (start, stop) = it.trim().split("=")[1].split("..")
            start.toInt()..stop.toInt()
        }
        CuboidRule(input[i].contains("on"), Cuboid(x, y, z))
    }

    public fun part1(): Int {
        val rebootSteps = rules.filter {
            it.region.x.first in -50..50 && it.region.x.last in -50..50 && it.region.y.first in -50..50 && it.region.y.last in -50..50 && it.region.z.first in -50..50 && it.region.z.last in -50..50
        }

        val points = hashSetOf<Point3D>()

        for (range in rebootSteps) {
            for (x in range.region.x) {
                for (y in range.region.y) {
                    for (z in range.region.z) {
                        if (range.on) {
                            points += Point3D(x, y, z)
                        } else {
                            points -= Point3D(x, y, z)
                        }
                    }
                }
            }
        }

        return points.size
    }

    public fun part2(): Long {
        val cuboidStorage = mutableListOf<CuboidRule>()

        rules.forEach {
            val overlaps = mutableListOf<CuboidRule>()

            cuboidStorage.forEach { cuboid ->
                val x1 = max(it.region.x.first, cuboid.region.x.first)
                val x2 = min(it.region.x.last, cuboid.region.x.last)
                val y1 = max(it.region.y.first, cuboid.region.y.first)
                val y2 = min(it.region.y.last, cuboid.region.y.last)
                val z1 = max(it.region.z.first, cuboid.region.z.first)
                val z2 = min(it.region.z.last, cuboid.region.z.last)

                if (x1 <= x2 && y1 <= y2 && z1 <= z2) {
                    overlaps += CuboidRule(!cuboid.on, Cuboid(x1..x2, y1..y2, z1..z2))
                }
            }

            cuboidStorage += overlaps
            if (it.on) {
                cuboidStorage += it
            }
        }

        return cuboidStorage.sumOf { (if (it.on) 1L else -1L) * (it.region.x.last - it.region.x.first + 1) * (it.region.y.last - it.region.y.first + 1) * (it.region.z.last - it.region.z.first + 1) }
    }
}

private data class Cuboid(public val x: IntRange, public val y: IntRange, public val z: IntRange) {
    override fun toString(): String = "[$x, $y, $z]"
}

private data class CuboidRule(public var on: Boolean, public val region: Cuboid) {
    override fun toString(): String = "CuboidRule[on=$on region=$region]"
}
