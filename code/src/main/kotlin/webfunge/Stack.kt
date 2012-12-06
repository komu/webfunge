package webfunge

import java.util.ArrayList

class Stack {
    private val stack = ArrayList<Int>()

    fun push(char: Char) {
        push(char.toCharCode())
    }

    fun push(char: Int) {
        stack.add(char)
    }

    fun clear() {
        stack.clear()
    }

    val size: Int
        get() = stack.size()

    fun pop(): Int {
        if (stack.isEmpty())
            throw IllegalStateException("stack underflow")
        val ch = stack[stack.size()-1]
        stack.remove(stack.size()-1)
        return ch
    }
}
