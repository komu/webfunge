package webfunge.core

interface UserConsole {
    fun writeChar(char: Int)
    fun writeInt(num: Int)
    fun readNumber(): Int
    fun readChar(): Char
    fun log(message: Any?)
}
