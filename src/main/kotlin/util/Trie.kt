package util

/**
 * The infamous "trie" that I can never find the source of; I often come back to this
 * cool pattern/algorithm to solve word-search problems. Turns out I implemented one
 * for AoC 2023, so I'll keep it handy this year.
 */
abstract class AbstractTrieNode {
    val children: Set<AbstractTrieNode> = mutableSetOf()
    open fun maybeAdd(node: AbstractTrieNode): AbstractTrieNode {
        children.plus(node)
        println("children = ${children}")
        return children.single { it.equals(node) }
    }
}

class TrieRoot : AbstractTrieNode() {
    override fun equals(other: Any?): Boolean {
        error("what")
    }
}

class TrieNode(val c: Char) : AbstractTrieNode() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TrieNode) return false

        if (c != other.c) return false

        return true
    }

    override fun hashCode(): Int {
        return c.hashCode()
    }
}

// WTF is the proper name for this lol
class TrieButt() : AbstractTrieNode() {
    override fun maybeAdd(node: AbstractTrieNode): AbstractTrieNode {
        error("nope")
    }

}

class Trie {
    val root = TrieRoot()
    fun add(word: String) {
        var node: AbstractTrieNode = root
        word.toCharArray().forEachIndexed { i, c ->
            node = node.maybeAdd(TrieNode(c))
        }
        node.maybeAdd(TrieButt())
    }
}

fun main() {
    val trie = Trie()
    trie.add("hello")
    trie.add("world")
    trie.add("hell")
    trie.add("worry")
    trie.add("word")
    println("trie = ${trie.root.children}")
}
