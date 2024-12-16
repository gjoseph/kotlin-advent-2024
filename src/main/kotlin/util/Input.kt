package util

import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.notExists
import kotlin.io.path.readLines

// === input/test files
fun inputFilePrefix(): String {
    // This is getting a bit tedious and magic, but
    val e = Thread.currentThread().stackTrace.last { it.fileName != "AdventOfKode2024.kt" }
    return e.className.substringBefore(".") + "/" + e.fileName!!.substringBefore(".kt")
}

fun readTestInput(testId: Int) = readInput("${inputFilePrefix()}_test${testId}.txt")
fun readDayInput() = readInput("${inputFilePrefix()}.txt")
private fun readInput(fileName: String): List<String> {
    val path = Path("src/main/kotlin/${fileName}")
    if (path.notExists()) {
        path.createFile()
        error("Created ${path} for you 🙄")
    }
    return path.readLines()
}