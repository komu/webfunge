package webfunge.core

// TODO: enum classes are not supported in JavaScript yet
class Direction(val name: String, val dx: Int, val dy: Int) {

    fun toString() = name
}

object Directions {
    val UP     = Direction("^", 0, -1)
    val DOWN   = Direction("v", 0, 1)
    val LEFT   = Direction("<", -1, 0)
    val RIGHT  = Direction(">", 1, 0)


    fun random(): Direction =
        when (Math.floor(Math.random() * 4)) {
            0 -> UP
            1 -> DOWN
            2 -> LEFT
            3 -> RIGHT
            else -> throw Exception("unexpected result")
        }
}
