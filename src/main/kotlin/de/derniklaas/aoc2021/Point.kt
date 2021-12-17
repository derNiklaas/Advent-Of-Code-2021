package de.derniklaas.aoc2021

public data class Point(public var x: Int, public var y: Int) {
    public operator fun plusAssign(other: Point) {
        x += other.x
        y += other.y
    }
}
