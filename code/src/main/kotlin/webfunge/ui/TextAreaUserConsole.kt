package webfunge.ui

import js.debug.console
import js.dom.html.HTMLTextAreaElement
import webfunge.core.UserConsole

class TextAreaUserConsole(val output: HTMLTextAreaElement) : UserConsole {

    override fun writeChar(char: Int) {
        output.value += char.toChar()
    }

    override fun writeInt(num: Int) {
        output.value += num
    }

    override fun readNumber(): Int = noImpl

    override fun readChar(): Char = noImpl

    fun clear() {
        output.value = ""
    }

    val isEmpty: Boolean
       get() = output.value == ""

    override fun log(message: Any?) {
        console.log(message)
    }
}
