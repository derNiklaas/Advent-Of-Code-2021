package de.derniklaas.aoc2021

public fun List<String>.mapToInt() = map(String::toInt)

public fun List<String>.splitAndMapToInt(deliminator: String = " ") = map { it.split(deliminator).mapToInt() }

public fun List<String>.splitAndMapToInt(deliminator: Regex) = map { it.split(deliminator).mapToInt() }
