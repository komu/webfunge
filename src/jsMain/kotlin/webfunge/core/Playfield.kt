package webfunge.core

class Playfield {

    val width = 80
    val height = 25
    private val state = Array<Char>(width * height) { ' ' }

    operator fun get(x: Int, y: Int): Char = state[index(x,y)]

    operator fun set(x: Int, y: Int, char: Char) {
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

    override fun toString(): String {
        val sb = StringBuilder()

        for (y in 0..<height) {
            for (x in 0..<width)
                sb.append(this[x,y])
            sb.append('\n')
        }

        return sb.toString()
    }
}
