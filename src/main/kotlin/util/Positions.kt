package util

/**
 * Utilities for 2d array structures, or in the case below,
 * just long arrays where we know the width of what would be a 2d array and try to be cleverer
 */
fun adjacentPositions(pos: Int, colWidth: Int, totalLength: Int, includeDiagonals: Boolean = true): List<Int> {
    // there is likely a cleverer way of doing this
    val col = pos % colWidth
    fun lineOf(pos: Int): Int = (pos - col) / colWidth
    val line = lineOf(pos)
    fun clampToLine(pos: Int, line: Int): Int {
        val startOfLineIncl = (line) * colWidth
        val endOfLineExcl = (line + 1) * colWidth
        return if (pos in startOfLineIncl..<endOfLineExcl) pos else -1
    }

    fun pos(relativeLine: Int, relativeCol: Int): Int {
        val res = ((line + relativeLine) * colWidth) + (col + relativeCol)
        return clampToLine(res, (line + relativeLine))
    }

    val positions = if (includeDiagonals) listOf(
        pos(-1, -1), pos(-1, 0), pos(-1, 1),
        pos(0, -1), pos(0, 1),
        pos(+1, -1), pos(+1, 0), pos(+1, 1),
    ) else {
        listOf(
            pos(-1, 0),
            pos(0, -1), pos(0, 1),
            pos(+1, 0),
        )
    }
    return positions
        .filter { it >= 0 && it < totalLength } // TODO it<totalLength should already be done by clamp() !?
}

// returns indexes of top, left, right, bottom elements
fun adjacentPositionsWithoutDiagonals(pos: Int, colWidth: Int, totalLength: Int): List<Int> {
    return adjacentPositions(pos, colWidth, totalLength, false)
}

