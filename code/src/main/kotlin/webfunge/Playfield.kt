package webfunge

import java.util.StringBuilder

class Playfield {

    val width = 80
    val height = 25
    private val state = Array<Char>(width * height) { ' ' }

    fun get(x: Int, y: Int): Char = state[index(x,y)]

    fun set(x: Int, y: Int, char: Char) {
        state[index(x,y)] = char
    }

    private fun index(x: Int, y: Int): Int {
        if (x < 0 || x >= width || y < 0 || y >= height)
            throw IndexOutOfBoundsException("parameters are out of bounds: $x,$y")

        return y*width + x
    }

    fun load(program: String) {
        reset()
        val lines = program.split("\n")
        for (y in lines.indices) {
            val line = lines[y]
            for (x in line.indices) {
                this[x,y] = line[x]
            }
        }
    }

    fun reset() {
        for (i in state.indices)
            state[i] = ' '
    }

    fun toString(): String {
        val sb = StringBuilder()

        for (y in 0..height-1) {
            for (x in 0..width-1)
                sb.append(this[x,y])
            sb.append('\n')
        }

        return sb.toString()
    }
}
