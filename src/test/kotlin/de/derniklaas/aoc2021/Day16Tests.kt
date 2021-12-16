package de.derniklaas.aoc2021

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day16Tests {

    @Test
    public fun part1() {
        Assertions.assertEquals(16, Day16("8A004A801A8002F478").part1())
        Assertions.assertEquals(12, Day16("620080001611562C8802118E34").part1())
        Assertions.assertEquals(23, Day16("C0015000016115A2E0802F182340").part1())
        Assertions.assertEquals(31, Day16("A0016C880162017C3686B18A3D4780").part1())
    }

    @Test
    public fun part2() {
        Assertions.assertEquals(3, Day16("C200B40A82").part2())
        Assertions.assertEquals(54, Day16("04005AC33890").part2())
        Assertions.assertEquals(7, Day16("880086C3E88112").part2())
        Assertions.assertEquals(9, Day16("CE00C43D881120").part2())
        Assertions.assertEquals(1, Day16("D8005AC2A8F0").part2())
        Assertions.assertEquals(0, Day16("F600BC2D8F").part2())
        Assertions.assertEquals(0, Day16("9C005AC2F8F0").part2())
        Assertions.assertEquals(1, Day16("9C0141080250320F1802104A08").part2())
    }
}
