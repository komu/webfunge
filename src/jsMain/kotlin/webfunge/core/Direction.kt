package webfunge.core

import webfunge.core.Direction.DOWN
import webfunge.core.Direction.LEFT
import webfunge.core.Direction.RIGHT
import webfunge.core.Direction.UP
import kotlin.random.Random

enum class Direction(val code: String, val dx: Int, val dy: Int) {
    UP("^", 0, -1),
    DOWN("v", 0, 1),
    LEFT("<", -1, 0),
    RIGHT (">", 1, 0);

    override fun toString() = code
}

fun Random.nextDirection() = when (nextInt(4)) {
    0 -> UP
    1 -> DOWN
    2 -> LEFT
    3 -> RIGHT
    else -> error("unexpected result")
}
