package util

import kotlin.time.TimedValue

/**
 * The cleaner shorthand for printing output.
 * I mean, is it really?
 */
fun Any?.println() = println(this)

/**
 * Just prints out the result in a somewhat understandable fashion.
 * Use {@link measureTimedValue} to pass a {@link } to also print how long the execution took/
 */
fun printResult(part: Int, res: Any?) {
    val resultStr = when (res) {
        is TimedValue<*> -> "${res.value} (took ${res.duration})"
        else -> res.toString()
    }
    println("➡️ ${inputFilePrefix().substringAfter("/")} - part $part : $resultStr")
}
