package webfunge

class Interpreter(val console: UserConsole) {

    val playfield = Playfield()
    val stack = Stack()
    var x = 0
    var y = 0
    var direction = Directions.RIGHT
    var stringMode = false
    var finished = true
    var steps = 0

    fun step(max: Int) {
        var steps = 0
        while (!finished && steps++ < max) {
            step()
        }
    }

    fun step() {
        val op = playfield[x,y]
        steps++

        if (stringMode) {
            if (op == '"')
                stringMode = false
            else
                stack.push(op)

        } else {
            when (op) {
                '0'  -> stack.push(0)
                '1'  -> stack.push(1)
                '2'  -> stack.push(2)
                '3'  -> stack.push(3)
                '4'  -> stack.push(4)
                '5'  -> stack.push(5)
                '6'  -> stack.push(6)
                '7'  -> stack.push(7)
                '8'  -> stack.push(8)
                '9'  -> stack.push(9)
                '+'  -> stack.push(stack.pop() + stack.pop())
                '-'  -> { val a = stack.pop(); val b = stack.pop(); stack.push(b-a) }
                '*'  -> stack.push(stack.pop() * stack.pop())
                '/'  -> { val a = stack.pop(); val b = stack.pop(); stack.push(b/a) }
                '%'  -> { val a = stack.pop(); val b = stack.pop(); stack.push(b%a) }
                '!'  -> stack.push(if (stack.pop() == 0) 1 else 0)
                '`'  -> { val a = stack.pop(); val b = stack.pop(); stack.push(if (b > a) 1 else 0) }
                '>'  -> direction = Directions.RIGHT
                '<'  -> direction = Directions.LEFT
                '^'  -> direction = Directions.UP
                'v'  -> direction = Directions.DOWN
                '?'  -> direction = Directions.random()
                '_'  -> direction = if (stack.pop() == 0) Directions.RIGHT else Directions.LEFT
                '|'  -> direction = if (stack.pop() == 0) Directions.DOWN else Directions.UP
                '"'  -> stringMode = true
                ':'  -> { val value = stack.pop(); stack.push(value); stack.push(value); }
                '\\' -> { val a = stack.pop(); val b = stack.pop(); stack.push(a); stack.push(b) }
                '$'  -> stack.pop()
                '.'  -> console.writeInt(stack.pop())
                ','  -> console.writeChar(stack.pop())
                '#'  -> move()
                'p'	 -> { val yy = stack.pop(); val xx = stack.pop(); val v = stack.pop(); playfield[xx,yy] = v.toChar() }
                'g'	 -> { val yy = stack.pop(); val xx = stack.pop(); stack.push(playfield[xx, yy]) }
                '&'	 -> stack.push(console.readNumber())
                '~'	 -> stack.push(console.readChar())
                '@'  -> finished = true
                ' '  -> { }
                else -> { console.log("unknown operator: '$op'")}
            }
        }

        move()
    }

    private fun move() {
        x = normalize(x + direction.dx, playfield.width)
        y = normalize(y + direction.dy, playfield.height)
    }

    private fun normalize(value: Int, max: Int): Int =
        if (value < 0)
            max + value
        else if (value >= max)
            value - max
        else
            value

    fun load(program: String) {
        playfield.load(program)
        stack.clear()
        x = 0
        y = 0
        steps = 0
        direction = Directions.RIGHT
        stringMode = false
        finished = false
    }
}
