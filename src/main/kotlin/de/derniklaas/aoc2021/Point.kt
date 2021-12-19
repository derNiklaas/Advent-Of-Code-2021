package de.derniklaas.aoc2021

import kotlin.math.abs

public data class Point(public var x: Int, public var y: Int) {
    public operator fun plusAssign(other: Point) {
        x += other.x
        y += other.y
    }
}

public data class Point3D(public var x: Int, public var y: Int, public var z: Int) {
    public companion object {
        public val NEUTRAL = Point3D(0, 0, 0)
    }

    public fun get(index: Int) = when (index) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw IndexOutOfBoundsException("Index $index is out of bounds (0..2)")
    }

    public fun rotate(direction: Int): Point3D {
        val c0 = direction % 3
        val c0s = 1 - ((direction / 3) % 2) * 2
        val c1 = (c0 + 1 + (direction / 6) % 2) % 3
        val c1s = 1 - (direction / 12) * 2
        val c2 = 3 - c0 - c1
        val c2s = c0s * c1s * (if (c1 == (c0 + 1) % 3) 1 else -1)
        return Point3D(get(c0) * c0s, get(c1) * c1s, get(c2) * c2s)
    }

    public fun apply(transformation: Transformation3D): Point3D {
        return when (transformation) {
            is Transformation3D.Identity -> this
            is Transformation3D.Ofs -> rotate(transformation.direction) + transformation.shift
            is Transformation3D.Combo -> apply(transformation.first).apply(transformation.second)
        }
    }

    public fun manhattanDistance(other: Point3D) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    public operator fun minus(other: Point3D) = Point3D(x - other.x, y - other.y, z - other.z)

    public operator fun plus(other: Point3D) = Point3D(x + other.x, y + other.y, z + other.z)

    override fun toString() = "[x: $x, y: $y, z: $z]"

    override fun equals(other: Any?): Boolean {
        if (other is Point3D) {
            return x == other.x && y == other.y && z == other.z
        }
        return false
    }

    override fun hashCode(): Int {
        return x * 31 + y * 31 * 31 + z * 31 * 31 * 31
    }

}
