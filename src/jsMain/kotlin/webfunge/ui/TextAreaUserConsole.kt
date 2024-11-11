package webfunge.ui

import kotlinx.browser.window
import org.w3c.dom.HTMLTextAreaElement
import webfunge.core.UserConsole

class TextAreaUserConsole() : UserConsole {

    val element = window.document.createElement("textarea") as HTMLTextAreaElement

    init {
        element.rows = 10
        element.cols = 80
        element.readOnly = true
    }

    override fun writeChar(char: Int) {
        element.value += char.toChar()
    }

    override fun writeInt(num: Int) {
        element.value += num
    }

    override fun readNumber(): Int = TODO()

    override fun readChar(): Char = TODO()

    fun clear() {
        element.value = ""
    }

    val isEmpty: Boolean
       get() = element.value == ""

    override fun log(message: Any?) {
        console.log(message)
    }
}
