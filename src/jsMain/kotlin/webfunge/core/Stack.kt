package webfunge.core

class Stack {
    private val stack = mutableListOf<Int>()

    fun push(char: Char) {
        push(char.code)
    }

    fun push(char: Int) {
        stack.add(char)
    }

    operator fun iterator() = stack.iterator()

    fun clear() {
        stack.clear()
    }

    val size: Int
        get() = stack.size

    fun pop(): Int {
        check(stack.isNotEmpty()) { "stack underflow" }

        return stack.removeLast()
    }
}
