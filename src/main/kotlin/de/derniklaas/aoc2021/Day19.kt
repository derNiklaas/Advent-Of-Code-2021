package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day19.txt").readLines()
    val day19 = Day19(input)
    println("Part 1: ${day19.part1()}")
    println("Part 2: ${day19.part2()}")
}

public class Day19(input: List<String>) {

    private val scanners: List<HashSet<Point3D>> = input.joinToString("\n").split("\n\n").map { point ->
        point.split("\n").drop(1).splitAndMapToInt(",").map { Point3D(it[0], it[1], it[2]) }
    }.map { it.toHashSet() }

    private val solutions = solve()

    public fun part1() = solutions.first.size

    public fun part2(): Int {
        val solved = solutions.second
        val list = buildList {
            for (s1 in solved) {
                for (s2 in solved) {
                    if (s1 != s2) {
                        add(s1!!.manhattanDistance(s2!!))
                    }
                }
            }
        }
        return list.maxOrNull()!!
    }

    private fun solve(): Pair<Set<Point3D>, Array<Point3D?>> {
        val size = scanners.size
        val cf = Array(size) { BooleanArray(size) }
        val cr = Array(size) { arrayOfNulls<Transformation3D.Ofs>(size) }

        val transformations = arrayOfNulls<Transformation3D>(size)
        val locations = scanners[0].toMutableSet()
        val s = arrayOfNulls<Point3D>(size)

        transformations[0] = Transformation3D.Identity
        s[0] = Point3D.NEUTRAL

        val foundSet = hashSetOf(0)
        val remaining = (1 until scanners.size).toHashSet()

        pair@ while (remaining.isNotEmpty()) {
            for (found in foundSet) {
                for (missing in remaining) {
                    val o = check(cf, cr, found, missing) ?: continue
                    val f = Transformation3D.Combo(o, transformations[found]!!)
                    transformations[missing] = f
                    s[missing] = Point3D.NEUTRAL.apply(f)
                    locations += scanners[missing].map { it.apply(f) }
                    foundSet += missing
                    remaining -= missing
                    continue@pair
                }
            }
            error("Couldn't find a solution.")
        }

        return Pair(locations.toSet(), s)
    }


    private fun check(
        cf: Array<BooleanArray>,
        cr: Array<Array<Transformation3D.Ofs?>>,
        found: Int,
        missing: Int
    ): Transformation3D.Ofs? {
        if (cf[found][missing]) return cr[found][missing]
        cf[found][missing] = true
        val scanner = scanners[found]
        for (direction in 0 until 24) {
            val v = scanners[missing].map { it.rotate(direction) }
            val shifts = buildSet { for (pu in scanner) for (pv in v) add(pu - pv) }
            for (shift in shifts) {
                val count = v.map { it + shift }.count { it in scanner }
                if (count >= 12) {
                    return Transformation3D.Ofs(direction, shift).also {
                        cr[found][missing] = it
                    }
                }
            }
        }
        return null
    }
}

sealed class Transformation3D {
    object Identity : Transformation3D()
    class Ofs(val direction: Int, val shift: Point3D) : Transformation3D()
    class Combo(val first: Transformation3D, val second: Transformation3D) : Transformation3D()
}
