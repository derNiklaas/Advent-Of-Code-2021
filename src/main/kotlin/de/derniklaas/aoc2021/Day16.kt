package de.derniklaas.aoc2021

import java.io.File

public fun main() {
    val input = File("src/main/resources/Day16.txt").readLines().first()
    val day16 = Day16(input)
    println("Part 1: ${day16.part1()}")
    println("Part 2: ${day16.part2()}")
}

public class Day16(public val input: String) {

    private val packet: Packet

    init {
        val hex = input.map { char ->
            val hex = char.digitToInt(16)
            hex.toString(2).padStart(4, '0')
        }.joinToString("")
        packet = parsePacket(StringBuilder(hex))
    }

    public fun part1() = packet.getVersionSum()

    public fun part2() = packet.body.evaluate()

    private fun parsePacket(input: StringBuilder) =
        when (val type = input.substring(3..5).toInt(2)) {
            4 -> {
                val version = input.takeAndDelete(3).toInt(2)
                input.delete(0, 3) // ignore type, already have it
                val bitGroups = mutableListOf<String>()
                while (true) {
                    if (!input.startsWith("1")) break
                    bitGroups += input.takeAndDelete(5)
                }

                require(input.startsWith("0"))
                bitGroups += input.takeAndDelete(5)

                val number = bitGroups.joinToString("") { it.drop(1) }.toLong(2)
                Packet(PacketHeader(version, type), LiteralValuePacket(number))
            }
            else -> {
                parseOperatorPacket(input)
            }
        }

    private fun parseOperatorPacket(input: StringBuilder): Packet {
        val version = input.takeAndDelete(3).toInt(2)

        val type = input.takeAndDelete(3).toInt(2)
        val lengthType = input.takeAndDelete(1).toInt(2)
        val subPackets = mutableListOf<Packet>()

        if (lengthType == 0) {
            val subPacketBits = input.takeAndDelete(15).toInt(2)
            val remainingBits = input.toString().length
            while (remainingBits - input.length < subPacketBits) {
                subPackets += parsePacket(input)
            }
        } else if (lengthType == 1) {
            val amountOfSubPackets = input.takeAndDelete(11).toInt(2)
            repeat(amountOfSubPackets) {
                subPackets += parsePacket(input)
            }
        }

        return Packet(PacketHeader(version, type), OperatorPacketBody.from(type, subPackets))
    }
}

public data class PacketHeader(public val version: Int, public val typeID: Int) {
    override fun toString(): String {
        return "(version=$version, type=$typeID)"
    }
}

public data class Packet(public val header: PacketHeader, public val body: PacketBody) {
    public fun getVersionSum(): Int {
        return header.version + body.getVersionSum()
    }

    override fun toString(): String {
        return "Packet(header=$header, body=$body)"
    }
}

public sealed class PacketBody {
    abstract fun getVersionSum(): Int
    abstract fun evaluate(): Long
}

public data class LiteralValuePacket(public val number: Long) : PacketBody() {
    override fun getVersionSum(): Int {
        return 0
    }

    override fun evaluate(): Long {
        return number
    }

    override fun toString(): String {
        return "LVP(number=$number)"
    }
}

public sealed class OperatorPacketBody(public val packets: List<Packet>) : PacketBody() {

    public companion object {
        public fun from(typeID: Int, packets: List<Packet>): PacketBody {
            return when (typeID) {
                0 -> SumPacketBody(packets)
                1 -> ProductPacketBody(packets)
                2 -> MinimumPacketBody(packets)
                3 -> MaximumPacketBody(packets)
                5 -> GreaterThenPacketBody(packets)
                6 -> LessThenPacketBody(packets)
                7 -> EqualsPacketBody(packets)
                else -> error("Unknown operator type $typeID")
            }
        }
    }

    override fun getVersionSum(): Int {
        return packets.sumOf { it.getVersionSum() }
    }

    override fun toString(): String {
        return "OPB(packets=$packets)"
    }
}

public class SumPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        return packets.sumOf { it.body.evaluate() }
    }

    override fun toString(): String {
        return "SumP(packets=$packets)"
    }
}

public class ProductPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        return packets.fold(1L) { acc, packet ->
            acc * packet.body.evaluate()
        }
    }

    override fun toString(): String {
        return "ProductP(packets=$packets)"
    }
}

public class MinimumPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        return packets.minOf { it.body.evaluate() }
    }

    override fun toString(): String {
        return "MinP(packets=$packets)"
    }
}

public class MaximumPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        return packets.maxOf { it.body.evaluate() }
    }

    override fun toString(): String {
        return "MaxP(packets=$packets)"
    }
}

public class GreaterThenPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        val (a, b) = packets
        return if (a.body.evaluate() > b.body.evaluate()) 1 else 0
    }

    override fun toString(): String {
        return "GTP(packets=$packets)"
    }
}

public class LessThenPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        val (a, b) = packets
        return if (a.body.evaluate() < b.body.evaluate()) 1 else 0
    }

    override fun toString(): String {
        return "LTP(packets=$packets)"
    }
}

public class EqualsPacketBody(packets: List<Packet>) : OperatorPacketBody(packets) {
    override fun evaluate(): Long {
        val (a, b) = packets
        return if (a.body.evaluate() == b.body.evaluate()) 1 else 0
    }

    override fun toString(): String {
        return "EP(packets=$packets)"
    }
}
