package webfunge

import js.debug.console
import js.dom.html.HTMLTextAreaElement
import js.dom.html.window

class UserConsole(val outputAreaId: String) {

    val output = window.document.getElementById(outputAreaId) as HTMLTextAreaElement

    fun writeChar(char: Int) {
        output.value += char.toChar()
    }

    fun writeInt(num: Int) {
        output.value += num
    }

    fun readNumber(): Int = noImpl

    fun readChar(): Char = noImpl

    fun clear() {
        output.value = ""
    }

    val isEmpty: Boolean
       get() = output.value == ""

    fun log(message: Any?) {
        console.log(message)
    }
}
