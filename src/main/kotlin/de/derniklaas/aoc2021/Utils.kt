package de.derniklaas.aoc2021

public fun List<String>.mapToInt() = map(String::toInt)

public fun List<String>.splitAndMapToInt(deliminator: String = " ") = map { it.split(deliminator).mapToInt() }

public fun List<String>.splitAndMapToInt(deliminator: Regex) = map { it.split(deliminator).mapToInt() }

public fun String.splitAndMapToInt(deliminator: String = " ") = split(deliminator).filter(String::isNotEmpty).mapToInt()

public fun String.splitAndMapToInt(deliminator: Regex) = split(deliminator).filter(String::isNotEmpty).mapToInt()

public fun Set<Int>.allPermutations(): Set<List<Int>> {
    if (this.isEmpty()) return emptySet()
    return _allPermutations(this.toList())
}

// from Tenfour04 on https://stackoverflow.com/a/59737650/9473036

private fun <T> _allPermutations(list: List<T>): Set<List<T>> {
    if (list.isEmpty()) return setOf(emptyList())

    val result: MutableSet<List<T>> = mutableSetOf()
    for (i in list.indices) {
        _allPermutations(list - list[i]).forEach { item -> result.add(item + list[i]) }
    }
    return result
}
