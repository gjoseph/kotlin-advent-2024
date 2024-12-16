package util

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Random utility methods i've used in 2023, might use again, or add more.
 */

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun quadratic(a: Long, b: Long, c: Long): Pair<Double, Double> {
    val s = Math.sqrt(((b * b) - (4 * a * c)).toDouble())
    val res1: Double = (-b - s) / (2 * a)
    val res2: Double = (-b + s) / (2 * a)
    return Pair(res1, res2)
}

// Doing this with stream/collector just got too messy
fun Iterable<Int>.multiply(): Int {
    var res: Int = 1
    for (element in this) {
        res *= element
    }
    return res
}

fun <T> List<T>.requireSize(requiredSize: Int): List<T> {
    return when (size) {
        requiredSize -> this
        else -> throw IllegalArgumentException("List has ${size} element(s) but must have ${requiredSize}.")
    }
}

fun <T> List<T>.toPair(): Pair<T, T> {
    this.requireSize(2)
    return Pair(this[0], this[1])
}

// I tried hard -- and failed -- find an idiomatic way to do this -- I thought one of scan, fold or one of these methods would probably work...
// ended up with this after a couple iterations
fun <T> List<T>.splitWhen(shouldSplitWhen: (T) -> Boolean): List<List<T>> {
    val output = mutableListOf<MutableList<T>>()
    this.forEach { i ->
        // if we add the first sublist outside the loop and the first entry is a splitter, we'll have an empty list at the start, which is unwanted
        val shouldSplit = shouldSplitWhen(i)
        if (output.isEmpty() || shouldSplit) {
            output.add(mutableListOf())
        }
        if (!shouldSplit) {
            output.last().add(i)
        }
    }
    return output.map { ml -> ml.toList() }.toList()
}

fun String.splitBySpace() = this.trim().split(Regex("\\s+"))
fun String.toInts() = splitBySpace().map(String::toInt)
fun String.toLongs() = splitBySpace().map(String::toLong)
