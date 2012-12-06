package webfunge.ui

import js.debug.console
import js.dom.html.HTMLTextAreaElement
import js.dom.html.window
import webfunge.core.UserConsole

class TextAreaUserConsole() : UserConsole {

    val element = window.document.createElement("textarea") as HTMLTextAreaElement;

    {
        element.rows = 10.toDouble()
        element.cols = 80.toDouble()
        element.readOnly = true
    }

    override fun writeChar(char: Int) {
        element.value += char.toChar()
    }

    override fun writeInt(num: Int) {
        element.value += num
    }

    override fun readNumber(): Int = noImpl

    override fun readChar(): Char = noImpl

    fun clear() {
        element.value = ""
    }

    val isEmpty: Boolean
       get() = element.value == ""

    override fun log(message: Any?) {
        console.log(message)
    }
}
